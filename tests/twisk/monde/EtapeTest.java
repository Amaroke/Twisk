package twisk.monde;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.outils.FabriqueNumero;

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
        FabriqueNumero singleton = FabriqueNumero.getInstance();
        singleton.reset();
        ar1 = new ActiviteRestreinte("ar1");
        ar2 = new ActiviteRestreinte("ar2", 5, 5);
        a1 = new Activite("a1");
        a2 = new Activite("a2", 5, 5);
        g1 = new Guichet("Guichet");
        g2 = new Guichet("Guichet", 5);
        se = new SasEntree();
        ss = new SasSortie();

    }

    @Test
    void ajouterSuccesseur() {
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
    void estUneActivite() {
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
    void estUnGuichet() {
        assertFalse(ar1.estUnGuichet());
        assertFalse(ar2.estUnGuichet());
        assertFalse(a1.estUnGuichet());
        assertFalse(a2.estUnGuichet());
        assertTrue(g1.estUnGuichet());
        assertTrue(g2.estUnGuichet());
        assertFalse(se.estUnGuichet());
        assertFalse(ss.estUnGuichet());
    }

    @Test
    void getNum() {
        System.out.println(ar1.getNum());
        assertEquals(ar1.getNum(), 0);
        assertEquals(ar2.getNum(), 1);
        assertEquals(a1.getNum(), 2);
        assertEquals(a2.getNum(), 3);
        assertEquals(g1.getNum(), 4);
        assertEquals(g2.getNum(), 5);
        assertEquals(se.getNum(), 6);
        assertEquals(ss.getNum(), 7);
    }

    @Test
    void toC() {
        se.ajouterSuccesseur(ar1);
        ar1.ajouterSuccesseur(ar2);
        ar2.ajouterSuccesseur(a1);
        a1.ajouterSuccesseur(a2);
        a2.ajouterSuccesseur(g1);
        g1.ajouterSuccesseur(g2);
        g2.ajouterSuccesseur(ss);
        ar2.ajouterSuccesseur(ss);
        assertEquals(se.toC(), "entrer(6); \ndelai(0,0); \ntransfert(6,0);\ndelai(0,0);\ntransfert(0,1);\ndelai(5,5);\ntransfert(1,2);\ndelai(0,0);\ntransfert(2,3);\ndelai(5,5);\ntransfert(3,4);\nP(0,0); \ntransfert(4,5); \nV(0,0);\nP(1,5); \ntransfert(5,7); \nV(1,5);\n");
    }
}