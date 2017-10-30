#pragma once

#include <iostream>
#include <fstream>
#include "TreeCreate.h"
#include "TreeNode.h"

using namespace std;

class TreeSerialize
{
	
public:
	TreeSerialize();
	~TreeSerialize();

	int serialize(string, TreeCreate&);

	void SaveNext(TreeNode*, ofstream&);

};

