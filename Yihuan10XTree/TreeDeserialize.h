#pragma once
#include <iostream>
#include <fstream>
#include "TreeCreate.h"
#include "TreeNode.h"
using namespace std;

class TreeDeserialize
{
public:
	TreeNode *root_node;

	TreeDeserialize()
	{
		root_node = new TreeNode();
	}

	void deserialize(string filename)
	{
		ifstream in;
		in.open(filename, ios::in);
		//cout << "Deserialize sequence:";
		RecoverNext(in, root_node);
		
		in.close();
	}

	void RecoverNext(ifstream &in , TreeNode *current_node)
	{
		int value;
		int child_num;
		in >> value >> child_num;
		current_node->value = value;
		current_node->child_num = child_num;
		//cout << value << " ";
		for (int i = 0; i < child_num ; i++)
		{
			TreeNode *child_node = new TreeNode();
			child_node->parent = current_node;
			current_node->child_vec.push_back(child_node);
			RecoverNext(in, child_node);
		}
		return;
	}
};