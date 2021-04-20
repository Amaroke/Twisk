package twisk;

public class TailleComposants {

    private static final TailleComposants instance = new TailleComposants();

    public static TailleComposants getInstance() {
        return instance;
    }

    public int getVBoxLong() {
        return 200;
    }

    public int getVBoxLarg() {
        return 75;
    }
}
