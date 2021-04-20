package twisk.mondeIG;

import java.util.Iterator;

/**
 * Classe abstraite EtapeIG.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public abstract class EtapeIG implements Iterable<PointDeControleIG> {
    private final String identifiant;
    private final int largeur;
    private final int hauteur;
    private String nom;
    private boolean selectionne;
    private int posX;
    private int posY;
    private boolean entre;
    private boolean sortie;
    private int temps;
    private int ecarttemps;
    private PointDeControleIG[] pdc = new PointDeControleIG[4];

    /**
     * Constructeur EtapeIG.
     *
     * @param nom  String
     * @param idf  String
     * @param larg int
     * @param haut int
     */
    public EtapeIG(String nom, String idf, int larg, int haut) {
        this.selectionne = false;
        this.nom = nom;
        this.identifiant = idf;
        this.largeur = larg;
        this.hauteur = haut;
        this.posX = (int) (Math.random() * (1000 - larg));
        this.posY = (int) (Math.random() * (700 - haut));
        pdc[0] = new PointDeControleIG(getPosX() + (getLargeur() / 2), getPosY(), getIdentifiant() + "n", this);
        pdc[1] = new PointDeControleIG(getPosX(), getPosY() + (getHauteur() / 2), getIdentifiant() + "o", this);
        pdc[2] = new PointDeControleIG(getPosX() + (getLargeur() / 2), getPosY() + getHauteur(), getIdentifiant() + "s", this);
        pdc[3] = new PointDeControleIG(getPosX() + getLargeur(), getPosY() + (getHauteur() / 2), getIdentifiant() + "e", this);
    }



    /**
     * Setter Selectionné.
     */
    public void setSelectionne() {
        selectionne = !selectionne;
    }

    /**
     * Setter PointDeControle.
     */
    public void setPDC() {
        pdc[0].setPosX(getPosX() + (getLargeur() / 2));
        pdc[0].setPosY(getPosY());

        pdc[1].setPosX(getPosX());
        pdc[1].setPosY(getPosY() + (getHauteur() / 2));

        pdc[2].setPosX(getPosX() + (getLargeur() / 2));
        pdc[2].setPosY(getPosY() + getHauteur());

        pdc[3].setPosX(getPosX() + getLargeur());
        pdc[3].setPosY(getPosY() + (getHauteur() / 2));
    }


    /**
     * Getter temps.
     *
     * @return int
     */
    public int getTemps(){
        return this.temps;
    }


    /**
     * Getter Identifiant.
     *
     * @return String
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * Getter du nom de l'étape.
     *
     * @return String
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter Nom.
     *
     * @param nnom String
     */
    public void setNom(String nnom) {
        this.nom = nnom;
    }

    /**
     * Getter hauteur.
     *
     * @return int
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * Getter PosX.
     *
     * @return int
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Getter largeur.
     *
     * @return int
     */
    public int getLargeur() {
        return largeur;
    }

    /*
    Partie Setter
     */

    /**
     * Setter Temps.
     *
     * @param temps int
     */
    public void setTemps(int temps){
        this.temps = temps;
    }

    /**
     * Setter ecartemps.
     *
     * @param etemps int
     */
    public void setEcarttemps(int etemps){
        this.ecarttemps = etemps;
    }

    /**
     * Setter PosX.
     *
     * @param posX int
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Getter PosY.
     *
     * @return int
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Setter PosY.
     *
     * @param posY int
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Setter entre.
     */
    public void setEstEntre() {
        entre = !entre;
    }

    /**
     * Setter sortie.
     */
    public void setEstSortie() {
        sortie = !sortie;
    }

    /**
     * Getter Selectionné.
     *
     * @return boolean
     */
    public boolean getSelectionne() {
        return selectionne;
    }

    /**
     * Getter PointDeControle.
     *
     * @param i int Index du points
     * @return PointDeControleIG
     */
    public PointDeControleIG getPdc(int i) {
        return pdc[i];
    }

    /**
     * Getter entre
     *
     * @return boolean
     */
    public boolean getEstUneEntre() {
        return this.entre;
    }

    /**
     * Getter sortie
     *
     * @return boolean
     */
    public boolean getEstUneSortie() {
        return this.sortie;
    }

    /**
     * Suppresions PDC.
     */
    public void suprPDC() {
        this.pdc = new PointDeControleIG[4];
    }

    /**
     * Fonction iterator PointDeControle.
     *
     * @return Iterator<PointDeControleIG>
     */
    @Override
    public Iterator<PointDeControleIG> iterator() {
        return new Iterator<>() {

            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < pdc.length && pdc[i] != null;
            }

            @Override
            public PointDeControleIG next() {
                return pdc[i++];
            }
        };
    }
}
