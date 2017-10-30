#include "TreeSerialize.h"


TreeSerialize::TreeSerialize()
{
}


TreeSerialize::~TreeSerialize()
{
}

int TreeSerialize::serialize(string filename, TreeCreate &tree)
{
	ofstream out;
	out.open(filename, ios::out | ios::trunc);
	//cout << "Serialize sequence:";
	SaveNext(tree.root_node, out);
	
	out.close();
	return 0;
}

void TreeSerialize::SaveNext(TreeNode *current_node , ofstream &out)
{
	int child_num = current_node -> child_num;
	int i = 0;
	current_node -> serialize(out);
	//cout << current_node->value << " ";
	while (i < child_num)
	{
		SaveNext(current_node->child_vec.at(i), out);
		i++;
	}
	return;
}
