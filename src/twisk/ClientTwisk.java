package twisk;

import twisk.monde.Activite;
import twisk.monde.ActiviteRestreinte;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.outils.ClassLoaderPerso;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Classe ClientTwisk
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public class ClientTwisk {

    public static void main(String[] args) {

        // Premier Monde
        Monde monde = new Monde();

        Activite zoo = new Activite("balade au zoo");
        Guichet guichet = new Guichet("accès au toboggan");
        Activite tob = new ActiviteRestreinte("toboggan");

        zoo.ajouterSuccesseur(guichet);
        guichet.ajouterSuccesseur(tob);

        monde.ajouter(zoo, guichet, tob);

        monde.aCommeEntree(zoo);
        monde.aCommeSortie(tob);

        try {
            ClassLoaderPerso ClassLoader = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
            Class<?> classSim = ClassLoader.loadClass("twisk.simulation.Simulation");
            Object oSim = classSim.getDeclaredConstructor().newInstance();
            Method msetNbClients = classSim.getDeclaredMethod("setNbClients", int.class);
            Method msimuler = classSim.getDeclaredMethod("simuler", twisk.monde.Monde.class);
            msetNbClients.invoke(oSim, 5);
            msimuler.invoke(oSim, monde);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }


        // Deuxième monde
        Monde monde2 = new Monde();

        Activite zoo2 = new Activite("balade au zoo", 3, 1);
        Guichet guichet2 = new Guichet("accès au toboggan", 2);
        Activite tob2 = new ActiviteRestreinte("toboggan", 2, 1);
        Activite tourniquet = new Activite("tourniquet", 3, 2);
        Activite parachute = new Activite("parachute", 4, 1);

        zoo2.ajouterSuccesseur(guichet2);
        guichet2.ajouterSuccesseur(tob2);
        tob2.ajouterSuccesseur(tourniquet);
        tourniquet.ajouterSuccesseur(parachute);

        monde2.ajouter(zoo2, guichet2, tob2, tourniquet, parachute);

        monde2.aCommeEntree(zoo2);
        monde2.aCommeSortie(parachute);

        try {
            ClassLoaderPerso ClassLoaderBis = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
            Class<?> classSimBis = ClassLoaderBis.loadClass("twisk.simulation.Simulation");
            Object oSimBis = classSimBis.getDeclaredConstructor().newInstance();
            Method msetNbClientsBis = classSimBis.getDeclaredMethod("setNbClients", int.class);
            Method msimulerBis = classSimBis.getDeclaredMethod("simuler", twisk.monde.Monde.class);
            msetNbClientsBis.invoke(oSimBis, 5);
            msimulerBis.invoke(oSimBis, monde2);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
