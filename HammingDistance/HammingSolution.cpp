#include <iostream>

using namespace std;

class Solution
{
public:
    int hammingDistance(int x, int y){
        int result = x^y;
        int distance = 0;
        while(result){
            result>>=1;
            if(result & 0x80000000)
            {
                distance++;
            }
            
        }
        return distance;
    }
};

int main()
{
    Solution s;
    int dist;
    dist = s.hammingDistance(1,4);
    cout << "the distance is : " << dist << endl;
    return 0;
}