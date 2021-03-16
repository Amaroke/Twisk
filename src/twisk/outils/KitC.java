package twisk.outils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class KitC {

    public KitC() {
        this.creerEnvironnement();
    }

    public void creerEnvironnement() {
        try {
            // Création du répertoire twisk sous /tmp. Ne déclenche pas d’erreur si le répertoire existe déjà.
            Path directories = Files.createDirectories(Paths.get("/tmp/twisk"));
            // Copie des deux fichiers programmeC.o et def.h depuis le projet sous /tmp/twisk.
            String[] liste = {"programmeC.o", "def.h", "codeNatif.o"};
            for (String nom : liste) {
                Path source = Paths.get(getClass().getResource("/codeC/" + nom).getPath());
                Path newdir = Paths.get("/tmp/twisk/");
                Files.copy(source, newdir.resolve(source.getFileName()), REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creerFichier(String codeC) {
        try {
            // Création du fichier client.c sous /tmp/twisk. Ne déclenche pas d’erreur si le fichier existe déjà.
            File chemin = new File("/tmp/twisk/client.c");
            FileWriter flotFiltre = new FileWriter("/tmp/twisk/client.c");
            flotFiltre.write(codeC);
            flotFiltre.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void compiler() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process p = runtime.exec("gcc -Wall -fPIC -c /tmp/twisk/client.c -o /tmp/twisk/client.o");
            // Récupération des messages sur la sortie standard.
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String ligne;
            while ((ligne = output.readLine()) != null) {
                System.out.println(ligne);
            }
            while ((ligne = error.readLine()) != null) {
                System.out.println(ligne);
            }
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void construireLaLibrairie() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process p = runtime.exec("gcc -shared /tmp/twisk/programmeC.o /tmp/twisk/codeNatif.o /tmp/twisk/client.o -o /tmp/twisk/libTwisk.so");
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String ligne;
            while ((ligne = output.readLine()) != null) {
                System.out.println(ligne);
            }
            while ((ligne = error.readLine()) != null) {
                System.out.println(ligne);
            }
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
