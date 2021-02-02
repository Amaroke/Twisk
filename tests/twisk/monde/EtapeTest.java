package twisk.monde;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {

    ActiviteRestreinte ar1; // Premier constructeur.
    ActiviteRestreinte ar2; // Deuxième constructeur.
    Activite a1; // Premier constructeur Activite
    Activite a2; // Deuxieme constructeur Activite
    Guichet g1; // Premier constructeur Guichet
    Guichet g2; // Deuxieme constructeur Guichet
    SasEntree se; // Constructeur du SasEntre
    SasSortie ss; // Constructeur du SasSortie

    @BeforeEach
    void setUp() {
        ar1 = new ActiviteRestreinte("ar1");
        ar2 = new ActiviteRestreinte("ar2", 5, 5);
        a1 = new Activite("a1");
        a2 = new Activite("a2", 5, 5);
        g1 = new Guichet ("Guichet");
        g2 = new Guichet ("Guichet", 5);
        se = new SasEntree();
        ss = new SasSortie();

    }

    @Test
    void testAjouterSuccesseur() {
        assertEquals(0, ar1.nbSuccesseurs());
        ar1.ajouterSuccesseur(new Activite("etape1"));
        assertEquals(1, ar1.nbSuccesseurs());
        ar1.ajouterSuccesseur(new Guichet("etape2"), new ActiviteRestreinte("etape3"));
        assertEquals(3, ar1.nbSuccesseurs());

        assertEquals(0, ar2.nbSuccesseurs());
        ar2.ajouterSuccesseur(new Activite("etape1"));
        assertEquals(1, ar2.nbSuccesseurs());
        ar2.ajouterSuccesseur(new Guichet("etape2"), new ActiviteRestreinte("etape3", 5, 5));
        assertEquals(3, ar2.nbSuccesseurs());

        assertEquals(0, a1.nbSuccesseurs());
        a1.ajouterSuccesseur(new Activite("etape1"));
        assertEquals(1, a1.nbSuccesseurs());
        a1.ajouterSuccesseur(new Guichet("etape2"), new Guichet("Guichet"));
        assertEquals(3, a1.nbSuccesseurs());

        assertEquals(0, a2.nbSuccesseurs());
        a2.ajouterSuccesseur(new Activite("etape1"));
        assertEquals(1, a2.nbSuccesseurs());
        a2.ajouterSuccesseur(new Guichet("etape2"), new Guichet("Guichet"));
        assertEquals(3, a2.nbSuccesseurs());

        assertEquals(0, g1.nbSuccesseurs());
        g1.ajouterSuccesseur(new Activite("etape1"));
        assertEquals(1, g1.nbSuccesseurs());
        g1.ajouterSuccesseur(new Guichet("etape2"), new ActiviteRestreinte("ActiviteRestrainte"));
        assertEquals(3, g1.nbSuccesseurs());

        assertEquals(0, g2.nbSuccesseurs());
        g2.ajouterSuccesseur(new Activite("etape1"));
        assertEquals(1, g2.nbSuccesseurs());
        g2.ajouterSuccesseur(new Guichet("etape2"), new ActiviteRestreinte("ActiviteRestrainte"));
        assertEquals(3, g2.nbSuccesseurs());

        assertEquals(0, se.nbSuccesseurs());
        se.ajouterSuccesseur(new Activite("etape1"));
        assertEquals(1, se.nbSuccesseurs());
        se.ajouterSuccesseur(new Guichet("etape2"), new ActiviteRestreinte("ActiviteRestrainte"));
        assertEquals(3, se.nbSuccesseurs());

        assertEquals(0, ss.nbSuccesseurs());
        ss.ajouterSuccesseur(new Activite("etape1"));
        assertEquals(1, ss.nbSuccesseurs());
        ss.ajouterSuccesseur(new Guichet("etape2"), new ActiviteRestreinte("ActiviteRestrainte"));
        assertEquals(3, ss.nbSuccesseurs());

    }

    @Test
    void testEstUneActivite() {
        assertTrue(ar1.estUneActivite());
        assertTrue(ar2.estUneActivite());
        assertTrue(a1.estUneActivite());
        assertTrue(a2.estUneActivite());
        assertFalse(g1.estUneActivite());
        assertFalse(g2.estUneActivite());
        assertTrue(se.estUneActivite());
        assertTrue(ss.estUneActivite());
    }

    @Test
    void testEstUnGuichet() {
        assertFalse(ar1.estUnGuichet());
        assertFalse(ar2.estUnGuichet());
        assertFalse(a1.estUnGuichet());
        assertFalse(a2.estUnGuichet());
        assertTrue(g1.estUnGuichet());
        assertTrue(g2.estUnGuichet());
        assertFalse(se.estUnGuichet());
        assertFalse(ss.estUnGuichet());
    }

}