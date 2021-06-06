package twisk.outils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Classe KitC
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public class KitC {

    private final FabriqueNumero f = FabriqueNumero.getInstance();
    private final int num_lib = f.getNumLib();

    /**
     * Constructeur du KitC.
     */
    public KitC() {
        this.creerEnvironnement();
    }

    /**
     * Fonction de création de l'environnement.
     */
    public void creerEnvironnement() {
        try {
            // Création du répertoire twisk sous /tmp. Ne déclenche pas d’erreur si le répertoire existe déjà.
            Files.createDirectories(Paths.get("/tmp/twisk"));
            // Copie des deux fichiers programmeC.o et def.h depuis le projet sous /tmp/twisk.
            String[] liste = {"programmeC.o", "def.h", "codeNatif.o", "lois.h", "lois.c"};
            for (String nom : liste) {
                InputStream source = Objects.requireNonNull(getClass().getResource("/codeC/" + nom)).openStream();
                File destination = new File("/tmp/twisk/" + nom);
                copier(source, destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de copier les fichier de ressouces/codeC.
     *
     * @param source Fichier source
     * @param dest   Fichier destination
     * @throws IOException en cas de probleme
     */
    private void copier(InputStream source, File dest) throws IOException {
        OutputStream destinationFile = new FileOutputStream(dest);
        // Lecture par segment de 0.5Mo
        byte[] buffer = new byte[512 * 1024];
        int nbLecture;
        while ((nbLecture = source.read(buffer)) != -1) {
            destinationFile.write(buffer, 0, nbLecture);
        }
        destinationFile.close();
        source.close();
    }

    /**
     * Permet de creer les fichier dans tmp/twisk.
     *
     * @param codeC String du code du monde
     */
    public void creerFichier(String codeC) {
        try {
            // Création du fichier client.c sous /tmp/twisk. Ne déclenche pas d’erreur si le fichier existe déjà.
            new File("/tmp/twisk/client.c");
            FileWriter flotFiltre = new FileWriter("/tmp/twisk/client.c");
            flotFiltre.write(codeC);
            flotFiltre.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de compiler le code C généré.
     */
    public void compiler() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process p = runtime.exec("gcc -Wall -fPIC -c /tmp/twisk/client.c -o /tmp/twisk/client.o");
            p.waitFor();
            Process p2 = runtime.exec("gcc -Wall -fPIC -c /tmp/twisk/lois.c -o /tmp/twisk/lois.o -lm");
            p2.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Execution de la commande pour construire la librairie.
     */
    public void construireLaLibrairie() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process p = runtime.exec("gcc -shared /tmp/twisk/programmeC.o /tmp/twisk/lois.o /tmp/twisk/codeNatif.o /tmp/twisk/client.o -o /tmp/twisk/libTwisk" + num_lib + ".so");
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter du numéro de la lib.
     * @return int
     */
    public int getNumLib() {
        return num_lib;
    }
}
