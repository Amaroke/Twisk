package twisk.monde;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GestionnaireEtapesTest {

    GestionnaireEtapes g;
    GestionnaireEtapes g1;
    GestionnaireEtapes g2;

    @BeforeEach
    void setUp() {
        g = new GestionnaireEtapes();
        g1 = new GestionnaireEtapes();
        g1.ajouter(new Activite("etape"));
        g2 = new GestionnaireEtapes();
        g2.ajouter(new Activite("etape"), new Activite("etape"), new Activite("etape"));
    }

    @Test
    void ajouter() {
        g.ajouter(new Activite("etape"));
        assertEquals(g.nbEtapes(), 1);
        g1.ajouter(new Activite("etape"));
        assertEquals(g1.nbEtapes(), 2);
        g2.ajouter(new Activite("etape"));
        assertEquals(g2.nbEtapes(), 4);
    }

    @Test
    void nbEtapes() {
        assertEquals(g.nbEtapes(), 0);
        assertEquals(g1.nbEtapes(), 1);
        assertEquals(g2.nbEtapes(), 3);
    }
}