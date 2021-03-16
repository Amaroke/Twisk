package twisk.outils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KitCTest {

    KitC kit;

    @BeforeEach
    void init() {
        kit = new KitC();
    }

    @Test
    void creerEnvironnement() {
        kit.creerEnvironnement();
    }

    @Test
    void creerFichier() {
        kit.creerFichier("test");
    }

    @Test
    void compiler() {
        kit.compiler();
    }

    @Test
    void construireLaLibrairie() {
        kit.construireLaLibrairie();
    }
}