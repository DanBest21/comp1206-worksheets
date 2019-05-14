#include <stdio.h>
#include <math.h>

int analyse(double *prod, double *sum, double d1, double d2);

int analyse(double *prod, double *sum, double d1, double d2)
{
	int i1, i2;

	// Round the two passed double values to the last integer.
	i1 = floor(d1);
	i2 = floor(d2);

	// Calculate the product and sum, and set them to the values of the appropriate pointer passed.
	*prod = i1 * i2;
	*sum = i1 + i2;

	// If the signs are different, or one value is equal to zero but the other is not, return -1.
	if ((signbit(d1) == 0 && signbit(d2) != 0) || (signbit(d1) == 0 && signbit(d2) != 0) || (d1 == 0 && d1 != d2) || (d1 != d2 && d2 == 0))
	{
		return -1;
	}
	
	// Otherwise return 1.
	return 1;
}