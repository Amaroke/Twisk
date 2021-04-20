package twisk.simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireClientsTest {

    GestionnaireClients gc;
    Client c1;
    Client c2;

    @BeforeEach
    void setUp() {
        gc = new GestionnaireClients(2);
        c1 = new Client(1);
        c2 = new Client(2);
        gc.setClients(1,2);
    }

    @Test
    void allerA() {
        Etape parc = new Activite("Parc");
        Etape manege = new Activite("Manege");
        gc.allerA(1,manege,1);
        assertEquals(gc.getListeClient().get(0).getEtape(), manege);
        assertEquals(gc.getListeClient().get(0).getRang(), 1);
        gc.allerA(1,parc,5);
        assertEquals(gc.getListeClient().get(0).getEtape(), parc);
        assertEquals(gc.getListeClient().get(0).getRang(), 5);

    }

    @Test
    void reset() {
        gc.reset();
        assertEquals(gc.getListeClient().size(), 0);
    }
}