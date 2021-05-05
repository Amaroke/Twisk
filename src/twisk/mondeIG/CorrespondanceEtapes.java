package twisk.mondeIG;

import twisk.monde.Etape;

import java.util.HashMap;

public class CorrespondanceEtapes {

    HashMap<EtapeIG, Etape> corresp;

    public CorrespondanceEtapes() {
        corresp = new HashMap<>(10);
    }

    public void ajouter(EtapeIG etig,Etape et){
        corresp.put(etig, et);
    }

    public Etape get(EtapeIG e){
        return corresp.get(e);
    }
}
