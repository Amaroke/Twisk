package twisk.mondeIG;

/**
 * Classe PointDeControleIG.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class PointDeControleIG {

    private final String identifiant;
    private final EtapeIG etape;
    private int posX;
    private int posY;

    /**
     * Constructeur PointDeControleIG.
     *
     * @param posX int
     * @param posY int
     * @param id   String
     * @param e    EtapeIG
     */
    public PointDeControleIG(int posX, int posY, String id, EtapeIG e) {
        this.posX = posX;
        this.posY = posY;
        identifiant = id;
        etape = e;
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
     * Setter PosX.
     *
     * @param x int
     */
    public void setPosX(int x) {
        posX = x;
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
     * Setter PosY
     *
     * @param y int
     */
    public void setPosY(int y) {
        this.posY = y;
    }

    /**
     * Getter etape.
     *
     * @return EtapeIG
     */
    public EtapeIG getEtape() {
        return etape;
    }
}
