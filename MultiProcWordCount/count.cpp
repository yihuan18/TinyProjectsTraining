#include <iostream>
#include <unistd.h>
#include <dirent.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/stat.h>
#include <string>
#include <string.h>
#include <map>
#include <sys/wait.h>
#include <fcntl.h>
#include <iterator>
#include <errno.h>
#include <fstream>


using namespace std;
//using std::string;

#define FILENUM 20
#define NAMELENGTH 100
#define PROC_READ_NUM 8
#define PROC_HANDLE_NUM 8


int file_num = 0;
char filename[FILENUM][NAMELENGTH];
int pipe_fd[2];            //from read_file_proc to handle_word_proc
int pipe_fd2[2];           //from handle_word_proc to main_proc

struct buf_block //length:4096 == PIPE_BUF store format in pipe_fd
{
    int length;
    char buf[4092];
};

struct word_map
{
    char word[47];
    int count;
};

int is_alphabet(char c)
{
    if(((c >= 'A' )&& (c <= 'Z') )|| ((c >= 'a' )&&( c <= 'z')))
        return 1;
    else
        return -1;
}

void read_file_proc(int file_no)
{
    cout << "child read_proc no : " << file_no << " created!" << endl;
    close(pipe_fd[0]); //close pipe read
    close(pipe_fd2[0]);
    close(pipe_fd2[1]);

    struct buf_block *read_buf;
    int file_fd , read_length , retval;

    read_buf = (struct buf_block *)malloc(sizeof(struct buf_block));
    while(file_no < file_num)
    {
        memset(read_buf , 0 , sizeof(struct buf_block));
        
        //----------------open file-----------------
        file_fd = open(filename[file_no] , O_RDONLY);
        if(file_fd == -1)
        {
            printf("open read file error!\n");
            return;
        }
        while(1)
        {
             //-----------------read file----------------
            read_length = read(file_fd , read_buf->buf , 4046);
            if(read_length == -1)
            {
                printf("read file error!\n");
                return;
            }
            if(read_length == 0)
            {
                cout << "read over!" << endl;
                break;
            }

            //if the last read char is an alphabet,continue reading until the last char is not an alphabet
            //while((read_buf->buf[read_length-1] >= 'A' && read_buf->buf[read_length-1] <= 'Z') || (read_buf->buf[read_length-1] >= 'a' && read_buf->buf[read_length-1]<= 'z'))
            //while(((isalpha(read_buf->buf[read_length-1])) == 1 )|| ((isalpha(read_buf->buf[read_length-1])) == 2))
            while(is_alphabet(read_buf->buf[read_length-1]) == 1)
            {
                retval = read(file_fd , &read_buf->buf[read_length] , 1);
                if(retval < 0)
                {
                    printf("read complete word fail!\n");
                    close(file_fd);
                    return;
                }
                if(retval == 0)
                {
                    read_length++;
                    break;
                }
                read_length+=retval; 
            }
            read_buf->length = read_length; //the length contains the last \0


            //-------------write pipe---------------
            //write length of 4096 <= PIPE_BUFF, to promise writing atomicly
            retval = write(pipe_fd[1] , read_buf , 4096);
            if(retval < 0)
            {
                printf("write pipe error!\n");
                close(pipe_fd[1]);
                close(file_fd);
                return;
            }

            memset(read_buf , 0 , sizeof(struct buf_block));
        }
        
        close(file_fd);
        file_no += PROC_READ_NUM;
    }

    close(pipe_fd[1]);
    free(read_buf);
    cout << "read file complete!" << endl;
    return;   
}


void handle_word_proc(int proc_no)
{
    cout << "child handle_word_proc no : " << proc_no << " created!" << endl;
    close(pipe_fd[1]); //close pipe write
    close(pipe_fd2[0]);

    map<string , int> word_count;   
    int retval , i ;
   
    while(1)
    {
        //-------------------read word list from pipe-----------------------
        struct buf_block read_buf;
        retval = read(pipe_fd[0] , &read_buf , 4096);
        //cout << "reading pipe_fd : length "<< read_buf.length << endl;
        if(retval == -1)
        {
            printf("handle_word_proc %d: read pipe error!\n" , proc_no);
            printf("error:%s\n" , strerror(errno));
            return;
         }
        if(retval == 0)
        {
            break;
        }
        
        i = 0;
        //-------------------count word num-----------------------
        while(i < read_buf.length)
        {
            string word;
            int j = 0;
            char word_string[47];
            memset(word_string , 0 , 47);

            while(is_alphabet(read_buf.buf[i]) == -1)
            {
                i++;
                if(i >= read_buf.length)
                    break;
            }

            if(i >= read_buf.length)
                break;

            while(is_alphabet(read_buf.buf[i]) == 1)
            {
                word_string[j] = read_buf.buf[i];
                j++;
                i++;
                if(i >= read_buf.length)
                    break;
            }

            word = word_string;
            //cout << "word read from pipe : "<< word << endl;
            ++word_count[word];    
        }//break when read_buf is traversed
    
    }// break when pipe_fd is finished
    cout << "handle_word_proc: read pipe over"<<endl;
    close(pipe_fd[0]);

    //-----------------------------store the counted result to pipe_fd2------------------
    
    map<string , int>::iterator iter = word_count.begin();
    //cout << word_count.size()<<endl;
    while(iter != word_count.end())
    {
        //cout << iter->first << " occurs " << iter->second << ((iter->second > 1)? " times" : " time") << endl;
        struct word_map count_map;
        count_map.count = 0;
        memset(count_map.word, 0 , 47);

        for(int i = 0; i < iter->first.size() ; i++)
            count_map.word[i] = iter->first[i];
        count_map.count = iter->second;
        int retval = write(pipe_fd2[1] , &count_map , sizeof(struct word_map));
        if(retval < 0)
        {
            cout << "write to pipe_fd2 error!" << endl;
            return;
        }
        ++iter;
    }

    close(pipe_fd2[1]);
    cout << "exit handle_word_proc" << endl;
    return;
}

void dir_traverse(char *dir_name)
{
    DIR *dir;
    struct dirent *dp;
    struct stat filestat;
    char dirname[NAMELENGTH];

    memset(dirname , 0 , sizeof(dirname));

    //打开目录
    if((dir = opendir(dir_name)) == NULL)
    {
        printf("open directory error!\n");
        return;
    }

    //读取目录内的文件
    while((dp = readdir(dir)) != NULL)
    {
        if(strncmp(dp->d_name , "." , 1) == 0)
            continue;
        
        if(strlen(dir_name) + strlen(dp->d_name) + 2 > NAMELENGTH)
        {
            printf("too long file name : %s/%s!\n" , dir_name , dp->d_name);
            return;
        }
        
        sprintf(dirname , "%s/%s" , dir_name , dp->d_name);
            
        if(lstat(dirname , &filestat) == -1)
        {
            printf("get file stat error!\n");
            return;
        }
        //printf("filestat get!\n");
        if((filestat.st_mode & S_IFMT) == S_IFDIR)
        {
            //printf("%s is a directory!\n" , dirname);
            dir_traverse(dirname);
        }
            
        else
        {
            //printf("%s is not a directory\n" , dirname);
            strcpy(filename[file_num] , dirname);
            file_num++;
        }
    }

    return;
}

int main(int argc , char *argv[])
{
    char dir[64];
    char result_file[64];
    int i , status;
    pid_t pid;
    
    //---------------------------initialization--------------------------------------
    memset(dir , 0 , sizeof(dir));
    
    if(pipe(pipe_fd) < 0)
    {
        printf("pipe error!\n");
        return -1;
    }
    
    if(pipe(pipe_fd2) < 0)
    {
        printf("pipe error!\n");
        return -1;
    }

    for(i = 0 ; i < FILENUM ; i++)
    {
        memset(filename[i] , 0 , sizeof(filename[i]));
    }

    //----------------------traverse the referred directory--------------------
    if(argc != 3)
    {
        printf("incompatible command : ./a.out dir_name result_file_name\n");
        return -1;
    }

    strcpy(dir , argv[1]);
    strcpy(result_file , argv[2]);
    
    dir_traverse(dir);
    
    //Debug
    for(i = 0 ; i < file_num ; i++)
    {
        printf("%s\n" , filename[i]);
    }

    //--------------------------------create chile process-----------------------
    for(i = 0 ; i < PROC_READ_NUM ; i++)
    {
        if((pid = fork()) < 0)
            printf("fork error!\n");
        else if(pid == 0)        //child 
        {
            read_file_proc(i);
            _exit(0);
        }
    }

    for(i = 0 ; i < PROC_HANDLE_NUM ; i++)
    {
        if((pid = fork()) < 0)
            printf("fork error!\n");
        else if(pid == 0)        //child 
        {
            handle_word_proc(i);
            _exit(0);
        }
    }
    
    close(pipe_fd[0]);
    close(pipe_fd[1]);
    close(pipe_fd2[1]);



    //---------------------------------collect the counted result----------------------
    map<string , int> word_count;
    string word;
    struct word_map count_map;
    while(1)
    {
        count_map.count = 0;
        memset(count_map.word , 0 , 47);

        int retval = read(pipe_fd2[0], &count_map , sizeof(struct word_map));
        if(retval < 0)
        {
            printf("read pipe_fd2 error!\n");
            return -1;
        }
        if(retval == 0)
            break;

        word = count_map.word;
        word_count[word] += count_map.count;
    }

    //--------------------------------wait exit chile process-------------------------
    for(i = 0 ; i < PROC_READ_NUM + PROC_HANDLE_NUM ; i++)
    {
        int retval = wait(&status);
        if(retval == 0 || retval == -1)
        {
            printf("wait wrong!\n");
        }
        cout << "exit proc num : " << i << endl;
    }    

    close(pipe_fd2[0]);




    map<string , int>::iterator iter = word_count.begin();
    ofstream out;
    out.open(result_file , ofstream::out | ofstream::trunc);

    while(iter != word_count.end())
    {
        //cout << iter->first << " occurs " << iter->second << ((iter->second > 1)? " times" : " time") << endl;
        if(out)
        {
            out << iter->first << " occurs " << iter->second << ((iter->second > 1)? " times" : " time") << endl;
        }
        ++iter;
    }
    
    out.close();   
    return 0;
}



