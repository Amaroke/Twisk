package twisk;

public class TailleComposants {
    private static final TailleComposants instance = new TailleComposants();
    //Vbox
    private final int VBoxLong = 200;
    private final int VBoxLarg = 75;

    private TailleComposants() {

    }

    public static TailleComposants getInstance() {
        return instance;
    }

    public int getVBoxLong() {
        return VBoxLong;
    }

    public int getVboxLarg() {
        return VBoxLarg;
    }
}
