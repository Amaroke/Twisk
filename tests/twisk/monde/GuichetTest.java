package twisk.monde;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuichetTest extends EtapeTest {
    Guichet g1;
    Guichet g2;

    @BeforeEach
    void setUp() {
        g1 = new Guichet("Guichet");
        g2 = new Guichet("Guichet", 5);
    }

    @Test
    void testEstUnGuichet() {
        assertTrue(g1.estUnGuichet());
        assertTrue(g2.estUnGuichet());
    }
}