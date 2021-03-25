package twisk.monde;

public class SasEntree extends Activite {

    public SasEntree() {
        super("SasEntree");
    }

    public String toC() {
        return "entrer(" + getNum() + "); \n" +
                "delai(" + 3 + "," + 1 + "); \n" +
                "transfert(" + getNum() + "," + getGestionnaireSuccesseurs().getEtapes().get(0).getNum() + ");\n" + getGestionnaireSuccesseurs().getEtapes().get(0).toC();
    }
}
