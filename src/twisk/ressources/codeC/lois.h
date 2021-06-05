#ifndef LOIS_H
#define LOIS_H

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

double delaiUniforme(int temps, int delta);
double delaiGauss(double moyenne, double ecartype);
double delaiExponentiel(double lambda);

#endif