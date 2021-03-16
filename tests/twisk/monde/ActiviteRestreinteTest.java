package twisk.monde;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActiviteRestreinteTest extends ActiviteTest {

    // Ici on test les fonctions exclusives à ActiviteRestreinte.

    @Override
    void toC() {
        se.ajouterSuccesseur(ar1);
        ar1.ajouterSuccesseur(ar2);
        ar2.ajouterSuccesseur(a1);
        a1.ajouterSuccesseur(a2);
        a2.ajouterSuccesseur(g1);
        g1.ajouterSuccesseur(g2);
        g2.ajouterSuccesseur(ss);
        ar2.ajouterSuccesseur(ss);
        assertEquals(se.toC(), "entrer(6); \ndelai(0,0); \ntransfert(6,0);\ndelai(0,0);\ntransfert(0,1);\ndelai(5,5);\ntransfert(1,2);\ndelai(0,0);\ntransfert(2,3);\ndelai(5,5);\ntransfert(3,4);\nP(0,0); \ntransfert(4,5); \nV(0,0);\nP(9,5); \ntransfert(5,7); \nV(9,5);\n");
    }
}