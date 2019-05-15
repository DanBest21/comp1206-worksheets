#include <stdio.h>
#include <math.h>

int analyse(double *prod, double *sum, double d1, double d2);

int analyse(double *prod, double *sum, double d1, double d2)
{
	int i1, i2;

	// Round the two passed double values to the nearest integer that is not greater than it.
	i1 = floor(d1);
	i2 = floor(d2);

	// Calculate the product and sum, and set them to the values of the appropriate pointer passed.
	*prod = i1 * i2;
	*sum = i1 + i2;

	// If the signs are the same (the product is positive) or both values are zero, return 1.
	if ((d1 * d2) > 0 || (d1 == 0 && d1 == d2))
	{
		return 1;
	}
	
	// Otherwise return -1.
	return -1;
}