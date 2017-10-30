#include <iostream>

using namespace std;

class Solution
{
private:
    int hammingDist;
public:
    Solution()
    {
        hammingDist = 0;
    }
    int hammingDistance(int x, int y){
        unsigned int shiftBit = 0x00000001;
        for(int i = 0 ; i < 31 ; i++)
        {
            cout << i << ":" << shiftBit << endl;
            if((x & shiftBit) != (y & shiftBit))
            {
                cout << "after " << i << "times shifting:" << endl;
                
                cout <<  (x & shiftBit) << endl;
                cout <<  (y & shiftBit) << endl;
                hammingDist++;
            }
            shiftBit <<= 1;    
        }
        return hammingDist;
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
