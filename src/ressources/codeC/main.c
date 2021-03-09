#include <stdlib.h>
#include <stdio.h>
#include "def.h"

#define NB_ETAPES 4
#define NB_CLIENTS 3
#define NB_GUICHETS 1

int main(int argc, char **argv)
{
    int * tabJetonsGuichet = malloc(sizeof(int)*NB_GUICHETS);
    tabJetonsGuichet[0] = 1;
    tabJetonsGuichet[1] = 1;
    int *processus = start_simulation(NB_ETAPES, 1, NB_CLIENTS, tabJetonsGuichet);
    printf("Les clients : ");
    for (int i = 0; i < NB_CLIENTS-1; ++i)
    {
        printf("%i, ", processus[i]);
    }
    printf("%i\n", processus[NB_CLIENTS-1]);
    int *clients = ou_sont_les_clients(NB_ETAPES, NB_CLIENTS);
    for (int k = 0; k < 20; k++)
    {
        clients = ou_sont_les_clients(NB_ETAPES, NB_CLIENTS);
        sleep(1);
        printf("\n");
        for (int j = 0; j < NB_ETAPES; ++j)
        {
            printf("Etape n°%i : ", j);
            printf("| Nombre de clients dans l'étape : %i | ", clients[(j * (NB_CLIENTS+1))]);
            for (int i = 0; i < clients[(j * (NB_CLIENTS+1))]; ++i)
            {
                printf(" %i ", clients[(j * (NB_CLIENTS+1)) + 1+i]);
            }
            printf("\n");
        }
    }
    nettoyage();
    return EXIT_SUCCESS;
}