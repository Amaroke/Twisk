package twisk.simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    Client c;
    Client c2;
    Client c3;

    @BeforeEach
    void setUp() {
        c = new Client(1);
        c2 = new Client(2);
    }

    @Test
    void allerA() {

        Etape tobbogan = new Activite("Toboggan");
        c.allerA(tobbogan,1);
        assertEquals(c.getEtape(), tobbogan);
        assertEquals(c.getRang(),1);

        Etape parc = new Activite("Parc");
        c2.allerA(parc, 2);
        assertEquals(c2.getEtape(),parc);
        assertEquals(c2.getRang(),2);
        c2.allerA(tobbogan, 3);
        assertEquals(c2.getEtape(),tobbogan);
        assertEquals(c2.getRang(),3);

    }
}