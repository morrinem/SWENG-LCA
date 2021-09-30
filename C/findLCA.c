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


int main ()
{
	struct node* a = newNode(1, NULL, NULL);
	struct node* b = newNode(2, a, NULL);
	struct node* c = newNode(3, NULL, NULL);
	struct node* d = newNode(4, b, c);
	struct node* e = newNode(5, NULL, NULL);
	struct node* f = newNode(6, NULL, NULL);
	struct node* g = newNode(7, e, f);
	struct node* h = newNode(8, NULL, NULL);
	struct node* i = newNode(9, g, h);
	struct node* j = newNode(10, d, i);
	
	struct node* ans1 = findLCA(j, a, e);
	struct node* ans2 = findLCA(j, a, b);
	struct node* ans3 = findLCA(j, e, f);
	struct node* ans4 = findLCA(j, f, h);
	printf("%i\n", ans1->data);
	printf("%i\n", ans2->data);
	printf("%i\n", ans3->data);
	printf("%i\n", ans4->data);
	
	//prints 10 2 7 9, same as java program
}
