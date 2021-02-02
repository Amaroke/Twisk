package twisk.monde;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ActiviteTest extends EtapeTest{
    Activite a1; // Constructeur d'activité
    Activite a2; // Constructeur d'activité avec t et e en param

    @BeforeEach
    void setUp() {
        a1 = new Activite("Activite");
        a2 = new Activite("Activite", 5 , 5);
    }

    @Test
    public void estUneActivite() {
        assertTrue(a1.estUneActivite());
        assertTrue(a2.estUneActivite());
    }
}