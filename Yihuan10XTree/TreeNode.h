#pragma once
#include <vector>
#include <fstream>
using namespace std;

class TreeNode
{
public:
	TreeNode *parent;
	vector<TreeNode*> child_vec;
	int child_num;
	int value;

	TreeNode()
	{
		//parent = nullptr;
		child_num = 0;
		value = 0;
	}
	
	TreeNode(int node_value , int node_child_num)
	{
		value = node_value;
		child_num = node_child_num;
	}

	~TreeNode()
	{
	}
	
	bool isequal(TreeNode *node)
	{
		if (value == node->value && child_vec.size() == node->child_vec.size())
			return true;
		else
			return false;
	}

	void serialize(ofstream &out)
	{
		out << value << " " << child_num<<" ";
	}

	void setParent(TreeNode *node)
	{
		parent = node;
	}

	void setChildNum(int num)
	{
		child_num = num;
	}

	void setChild(TreeNode *node)
	{
		child_vec.push_back(node);
	}
};