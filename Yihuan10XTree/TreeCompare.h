#pragma once
#include "TreeNode.h"
class TreeCompare
{
public:
	bool compare(TreeNode *tree_root1, TreeNode *tree_root2)
	{
		return compareNode(tree_root1, tree_root2);
	}

	bool compareNode(TreeNode *node1, TreeNode *node2)
	{
		if (node1->isequal(node2) != true)
			return false;
		else 
		{
			for (int i = 0 ; i < node1->child_num; i++)
			{
				if (compareNode(node1->child_vec.at(i), node2->child_vec.at(i)) == false)
					return false;
			}
		}
		return true;
	}
};