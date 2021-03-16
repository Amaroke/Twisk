package twisk.monde;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MondeTest {

    Monde m;
    Monde m1;
    Monde m2;
    Monde m3;

    @BeforeEach
    void setUp() {
        m = new Monde();
        m1 = new Monde();
        m1.ajouter(new Activite("etape2"), new ActiviteRestreinte("etape3"), new Guichet("etape4"), new ActiviteRestreinte("etape5"));
        m2 = new Monde();
        m2.ajouter(new Guichet("Guichet1"), new Guichet("Guichet2"));

        m3 = new Monde();
        Activite a = new Activite("Activite");
        m3.ajouter(a);
        m3.aCommeEntree(a);
        m3.aCommeSortie(a);
    }

    @Test
    void aCommeEntree() {

    }

    @Test
    void aCommeSortie() {

    }

    @Test
    void ajouter() {
        assertEquals(0, m.nbEtapes());
        m.ajouter(new Activite("etape1"));
        assertEquals(1, m.nbEtapes());
        m.ajouter(new Guichet("etape2"), new ActiviteRestreinte("etape3"));
        assertEquals(3, m.nbEtapes());

        m1.ajouter(new Activite("etape1"));
        assertEquals(5, m1.nbEtapes());

        m2.ajouter(new Guichet("guichet3"));
        assertEquals(3, m2.nbEtapes());
    }

    @Test
    void nbEtapes() {
        assertEquals(0, m.nbEtapes());
        assertEquals(4, m1.nbEtapes());
        assertEquals(2, m2.nbEtapes());
    }

    @Test
    void nbGuichets() {
        assertEquals(0, m.nbGuichets());
        assertEquals(1, m1.nbGuichets());
        assertEquals(2, m2.nbGuichets());
    }

    @Test
    void toC() {
        assertEquals(m3.toC().toString(), "#include <stdlib.h>\n#include <stdio.h>\n#include \"def.h\" \n \nvoid simulation(int ids){ \nentrer(70); \ndelai(0,0); \ntransfert(70,72);\ndelai(0,0);\ntransfert(72,71);\n}");
    }
}
