#include <unistd.h>
#include <fcntl.h>
#include <pthread.h>
#include <error.h>
#include <string.h>

#define BUFFSIZE 4096 

int fd_pipe[2];
int fd_read , fd_write;
int THREAD_NUM_READ = 1;
int THREAD_NUM_WRITE = 10;
struct file_content
{
    int offset;
    char content[BUFFSIZE];
    int content_length;
};

void *thread_read(void *arg)
{
    struct file_content *content;
    int thread_sequence = (int)arg;
    int retval;
    
    while(1)
    {
        content = (struct file_content *)malloc(sizeof(struct file_content));
        content->content_length = pread(fd_read , content->content , BUFFSIZE , thread_sequence*BUFFSIZE);
        content->offset = thread_sequence * BUFFSIZE;
        if(content->content_length == -1)
        {
            printf("pread error in thread %d\n" , (int)arg);
            pthread_exit((void *)-1);
        }
        if(content->content_length == 0)
        {
            free(content);
            break;
        }
        
        retval = write(fd_pipe[1] , &content , sizeof(struct file_content *));
        if(retval == -1)
        {
            printf("write to pipe error in read thread %d!\n" , (int)arg);
            pthread_exit((void *)-1);
        }
        
        thread_sequence += THREAD_NUM_READ; 
    }

    pthread_exit((void *)0);
}

void *thread_write(void *arg)
{
    int retval;
    struct file_content *content;
    content = (struct file_content *)malloc(sizeof(struct file_content));
    while(1)
    {
        retval = read(fd_pipe[0] , &content , sizeof(struct file_content *));
        if(retval == -1)
        {
            printf("read from pipe error in write thread %d!\n" , (int)arg);
            pthread_exit((void *)-1);
        }
        if(retval == 0)
        {
            free(content);
            break;
        }

        retval = pwrite(fd_write , content->content , content->content_length , content->offset);
        if(retval == -1)
        {
            printf("pwrite error in write thread %d\n" , (int)arg);
            pthread_exit((void *)-1);
        }
    }
    pthread_exit((void *)0);
}

int main(int argc , char *argv[])
{
    pthread_t tid_read[THREAD_NUM_READ];
    pthread_t tid_write[THREAD_NUM_WRITE];
    int i , errno;
    void *tret;

    if(argc != 3)
    {
        printf("incompatible command : ./a.out readfiledirectory writefiledirectory\n");
        return 0;
    }

    if(pipe(fd_pipe) == -1)
        printf("failed to create pipeBuffer!\n");
     
    fd_read = open(argv[1] , O_RDONLY);
    if(fd_read == -1)
    {
        printf("open readfile error!\n");
        return -1;
    }
    
    fd_write = open(argv[2] , O_WRONLY | O_CREAT | O_TRUNC , S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);
    if(fd_write == -1)
    {
        printf("open writefile error\n");
        return -1;
    }

    //create read pthread
    for(i = 0 ; i < THREAD_NUM_READ ; i++)
    {
        errno = pthread_create(&tid_read[i] , NULL , thread_read , (void *)i);
        if(errno != 0)
        {
            printf("read thread create error : %s\n" , strerror(errno));
            return -1;
        }
    }

    //create write pthread
    for(i = 0 ; i < THREAD_NUM_WRITE ; i++)
    {
        errno = pthread_create(&tid_write[i] , NULL , thread_write , (void *)i);
        if(errno != 0)
        {
            printf("write thread create error : %s\n" , strerror(errno));
            return -1;
        }
    }

    //wait for read thread exit
    for(i = 0 ; i < THREAD_NUM_READ ; i++)
    {
        errno = pthread_join(tid_read[i] , &tret);
        if(errno != 0)
        {
            printf("can't join with read thread %d\n" , i);
            return -1;
        }
        printf("read thread %d exits!\n" , i);
    } 

    close(fd_read);
    close(fd_pipe[1]);

    //wait for write thread exit
    for(i = 0 ; i < THREAD_NUM_WRITE ; i++)
    {
        errno = pthread_join(tid_write[i] , tid_write[i]);
        if(errno != 0)
        {
            printf("can't join with write thread %d\n" , i);
        }
        printf("write thread %d exits!\n" , i);
    }
    
    close(fd_write);
    close(fd_pipe[0]);

    return 0;
}




