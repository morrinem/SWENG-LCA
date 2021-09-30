#include <stdio.h>
#include <stdlib.h>

struct node
{
	int data;
	struct node* left;
	struct node* right;
};

struct node* newNode(int data, struct node* left, struct node* right)
{
	struct node* node = (struct node*)malloc(sizeof(struct node));
	node->data = data;
	node->left = left;
	node->right = right;
	return node;
}

struct node* findLCA(struct node* root, struct node* a, struct node* b)
{
	if (root == NULL || a == NULL || b == NULL) return NULL;
	if (root->data == a->data || root->data == b->data) return root;
	
	struct node* left = findLCA(root->left, a, b);
	struct node* right = findLCA(root->right, a, b);
	
	if (left != NULL && right != NULL) return root;
	if (left == NULL) return right;
	return left;
}

int getData(struct node* node)
{
	return node->data;
}