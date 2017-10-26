#include <cstdio>
#include <fstream>
#include "TreeNode.h"
#include "TreeCreate.h"
#include "TreeSerialize.h"
#include "TreeDeserialize.h"
#include "TreeCompare.h"
#include <sys/time.h>

using namespace std;

int main()
{
	struct timeval tv;
	long int time_begin,time_after;
	double average_time;
	printf("hello from Yihuan10XTree!\n");

	//create YihuanTree(x,y) with x depth and y width
	TreeCreate YihuanTree(7,10);
	cout << "total created node num : " << YihuanTree.tree_node_num << endl;

	//serialize created tree to file
	TreeSerialize serialization;
	serialization.serialize("tree", YihuanTree);

	//deserialize saved tree and rockon by time
	TreeDeserialize deserialization;

	gettimeofday(&tv, NULL);
	time_begin = tv.tv_sec * 1000000 + tv.tv_usec;

	deserialization.deserialize("tree");

	gettimeofday(&tv, NULL);
	time_after = tv.tv_sec * 1000000 + tv.tv_usec;

	average_time = (double)(time_after - time_begin) / YihuanTree.tree_node_num;
	cout << "deserialization YihuanTree take time : ";
	cout << time_after - time_begin << "us" <<endl;
	cout << "average time : " << average_time << endl;
	
	//compare original tree and deserialized tree
	TreeCompare comparation;
	bool equal;
	equal = comparation.compare(YihuanTree.root_node, deserialization.root_node);
	cout << "comparation result : " << equal << endl;

    return 0;
} 