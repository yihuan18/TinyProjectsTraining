#pragma once
#include <iostream>
#include "TreeNode.h"
#include <ctime>
using namespace std;

class TreeCreate
{
public:
	TreeNode *root_node;
	int tree_depth;
	int max_leaf_num;
	int depth_count;
	int tree_node_num;

	TreeCreate(int depth, int width)
	{
		tree_depth = depth;
		max_leaf_num = width;
		tree_node_num = 0;
		root_node = new TreeNode();

		root_node -> value = Random(1,100);
		root_node -> parent = nullptr;
		root_node -> child_num = 0;
		depth_count = 0;
		srand((unsigned int)time(0));
		child_create(root_node);
		cout << "Tree Create complete!" << endl;
	}

	int child_create(TreeNode *parent) //create a tree with tree_depth deep and max_leaf_num width , if no root is specified , enter the parameter "nullptr"
	{
		TreeNode *parent_node;
	
		parent_node = parent;

		depth_count++;
		if (depth_count == tree_depth)
		{
			depth_count--;
			return 0;
		}
		
		parent_node->child_num = Random(0, max_leaf_num);
		if (depth_count != 6 && parent_node->child_num == 0)
			parent_node->child_num++;
		for (int i = 0; i < parent_node->child_num; i++)
		{
			TreeNode *child_node = new TreeNode(Random(1, RAND_MAX),0);
			tree_node_num++;
			child_node->parent = parent_node;

			parent_node->child_vec.push_back(child_node);
			child_create(child_node);
		}

		depth_count--;
		return 0;
	}

	int Random(int min, int max)
	{
		//srand((unsigned int)time(0));
		return rand() % max + min;
	}
};
