#include <stdio.h>

typedef int* CHECKARR;

CHECKARR checkNEW(int N);
int checkSTORE(CHECKARR as, int N, int row, int col, int val);
int checkFETCH(CHECKARR as, int N, int row, int col);

CHECKARR checkNEW(int N)
{
	// Return a pointer to a block of memory that is N * N * int (4 bits) worth of memory - i.e. enough for N * N ints.
	return malloc(sizeof(int) * (N * N));
}

int checkSTORE(CHECKARR as, int N, int row, int col, int val)
{
	// If the parity of row and col is the same (they have the same result from modulo 2), and they are both within range of the array:
	if ((row % 2 == col % 2) && (row < N && col < N) && (row >= 0 && col >= 0))
	{		
		// Set the passed value to the correct element, which is determined by (N * row) + col and return 1 to indicate success.
		as[(N * row) + col] = val;
		return 1;
	}
	
	// Otherwise return -1 and don't set the value as it is at an invalid position.
	return -1;
}

int checkFETCH(CHECKARR as, int N, int row, int col)
{
	// If the parity of row and col is the same, and they are both within range of the array:
	if ((row % 2 == col % 2) && (row < N && col < N) && (row >= 0 && col >= 0))
	{		
		// Return the corresponding element.
		return as[(N * row) + col];
	}

	// Otherwise return -1 to indicate the value cannot be fetched.
	return -1;
}