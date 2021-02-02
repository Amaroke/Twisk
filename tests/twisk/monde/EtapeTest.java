package twisk.monde;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {

    ActiviteRestreinte ar1; // Premier constructeur.
    ActiviteRestreinte ar2; // Deuxième constructeur.
    // ###Définir le reste.###

    @BeforeEach
    void setUp() {
        ar1 = new ActiviteRestreinte("ar1");
        ar2 = new ActiviteRestreinte("ar2");
    }

    @Test
    void testAjouterSuccesseur() {
        assertEquals(0, ar1.nbSuccesseurs());
        ar1.ajouterSuccesseur(new Activite("etape1"));
        assertEquals(1, ar1.nbSuccesseurs());
        ar1.ajouterSuccesseur(new Guichet("etape2"), new ActiviteRestreinte("etape3"));
        assertEquals(3, ar1.nbSuccesseurs());
    }

    @Test
    void testEstUneActivite() {
        assertTrue(ar1.estUneActivite());
        assertTrue(ar2.estUneActivite());
    }

    @Test
    void testEstUnGuichet() {
        assertFalse(ar1.estUnGuichet());
        assertFalse(ar2.estUnGuichet());
    }

}