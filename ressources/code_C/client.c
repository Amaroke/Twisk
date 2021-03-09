#include <stdlib.h>
#include <stdio.h>
#include "def.h"

#define SAS_ENTREE 0
#define GUICHET 1
#define ACTIVITE 2
#define SAS_SORTIE 3

void simulation(int ids)
{
    entrer(SAS_ENTREE);
    delai(5, 1);
    transfert(SAS_ENTREE, GUICHET);
    P(ids, 1);
    transfert(GUICHET, ACTIVITE);
    delai(5, 1);
    V(ids, 1);
    transfert(ACTIVITE, SAS_SORTIE);
}