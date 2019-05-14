#include <stdio.h>

typedef int* CHECKARR;

CHECKARR checkNew(int N);
int checkSTORE(CHECKARR as, int N, int row, int col, int val);
int checkFETCH(CHECKARR as, int N, int row, int col);

CHECKARR checkNew(int N)
{
	return (int*)malloc(sizeof(int) * (N * N));
}

int checkSTORE(CHECKARR as, int N, int row, int col, int val)
{
	if ((row % 2 == col % 2) && (row < N && col < N) && (row >= 0 && col >= 0))
	{
		as[(N * row) + col] = val;
		return 1;
	}
	
	return -1;
}

int checkFETCH(CHECKARR as, int N, int row, int col)
{
	if ((row % 2 == col % 2) && (row < N && col < N) && (row >= 0 && col >= 0))
	{
		return as[(N * row) + col];
	}

	return -1;
}

int main(void)
{
	const int N = 6;
	CHECKARR arr = checkNew(N);

	printf("checkSTORE 1: %i\n", checkSTORE(arr, N, 2, 2, 56));
	printf("checkSTORE 2: %i\n", checkSTORE(arr, N, 3, 5, 112));
	printf("checkSTORE 3: %i\n", checkSTORE(arr, N, 0, 0, 5667));
	printf("checkSTORE 4: %i\n", checkSTORE(arr, N, 0, 1, 23));
	printf("checkSTORE 5: %i\n", checkSTORE(arr, N, 2, 5, 234));
	printf("checkSTORE 6: %i\n", checkSTORE(arr, N, 7, 5, 1112));

	printf("checkFETCH 1: %i\n", checkFETCH(arr, N, 3, 5));
	printf("checkFETCH 2: %i\n", checkFETCH(arr, N, 0, 1));
	printf("checkFETCH 3: %i\n", checkFETCH(arr, N, 10, 10));
}