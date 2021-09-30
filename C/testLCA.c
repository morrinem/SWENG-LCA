#include <stdio.h>
#include <stdlib.h>
#include <check.h>
#include "findLCA.h"

#define	ck_assert_int_eq(X, Y)	_ck_assert_int(X, ==, Y)

START_TEST (nullTest)
{
	struct node* a = newNode(1, NULL, NULL);
	ck_assert_ptr_eq(findLCA(NULL, NULL, NULL), NULL);
	ck_assert_ptr_eq(findLCA(a, NULL, NULL), NULL);
	ck_assert_ptr_eq(findLCA(NULL, a, NULL), NULL);
	ck_assert_ptr_eq(findLCA(NULL, NULL, a), NULL);
	ck_assert_ptr_eq(findLCA(a, a, a), a);
	free(a);
}
END_TEST

START_TEST (regularCase)
{
	struct node* nodeList[14];
	nodeList[0] = newNode(1, NULL, NULL);
	nodeList[1] = newNode(2, nodeList[0], NULL);
	nodeList[2] = newNode(3, NULL, NULL);
	nodeList[3] = newNode(4, nodeList[1], nodeList[2]);
	nodeList[4] = newNode(5, NULL, NULL);
	nodeList[5] = newNode(6, NULL, NULL);
	nodeList[6] = newNode(7, nodeList[4], nodeList[5]);
	nodeList[7] = newNode(8, NULL, NULL);
	nodeList[8] = newNode(9, nodeList[6], nodeList[7]);
	nodeList[9] = newNode(10, nodeList[3], nodeList[8]);
	
	nodeList[10] = findLCA(nodeList[9], nodeList[0], nodeList[4]);
	nodeList[11] = findLCA(nodeList[9], nodeList[0], nodeList[1]);
	nodeList[12] = findLCA(nodeList[9], nodeList[4], nodeList[5]);
	nodeList[13] = findLCA(nodeList[9], nodeList[5], nodeList[7]);
	
	ck_assert_int_eq(getData(nodeList[10]), 10);
	ck_assert_int_eq(getData(nodeList[11]), 2);
	ck_assert_int_eq(getData(nodeList[12]), 7);
	ck_assert_int_eq(getData(nodeList[13]), 9);
	
	//only free to 10, since 10, 11, 12, 13 should be repeats
	for (int i = 0; i < 10; i++) free(nodeList[i]);
}
END_TEST

Suite* suiteNull(void)
{
	Suite* s;
	TCase* tc1;
	TCase* tc2;
	
	s = suite_create("test LCA suite");
	
	tc1 = tcase_create("case null");
	tc2 = tcase_create("case regular");
	
	tcase_add_test(tc1, nullTest);
	tcase_add_test(tc2, regularCase);
	
	suite_add_tcase(s, tc1);
	suite_add_tcase(s, tc2);
	return s;
}

int main(void)
{
	int no_failed = 0;
	SRunner* runner;
	Suite* suite_null = suiteNull();
	runner = srunner_create(suite_null);
	
	
	srunner_run_all(runner, CK_NORMAL);
	no_failed = srunner_ntests_failed(runner);
	srunner_free(runner);
	return (no_failed == 0) ? EXIT_SUCCESS : EXIT_FAILURE;
}