#include <stdlib.h>
#include <stdio.h>
#include "def.h"

#define SAS_ENTREE 0
#define ACTIVITE 1
#define GUICHET 2
#define ACTIVITE2 3
#define GUICHET2 4
#define ACTIVITE3 5
#define SAS_SORTIE 6

void simulation(int ids) {
    entrer(SAS_ENTREE);
    delai(5,3);
    transfert(SAS_ENTREE, ACTIVITE);
    delai(3, 2);
    transfert(ACTIVITE, GUICHET);
    P(ids, 1);
    transfert(GUICHET, ACTIVITE2);
    delai(3, 2);
    V(ids, 1);
    delai(3, 2);
    transfert(ACTIVITE2, GUICHET2);
    P(ids, 2);
    transfert(GUICHET2, ACTIVITE3);
    delai(3, 2);
    V(ids, 2);
    delai(3, 2);
    transfert(ACTIVITE3, SAS_SORTIE);
}