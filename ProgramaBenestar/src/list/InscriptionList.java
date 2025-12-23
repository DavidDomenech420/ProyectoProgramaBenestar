package list;

import dades.Inscriptions;

public class InscriptionList {
    Inscriptions[] list;
    int numElems;

    
    public InscriptionList(int num) {
        list = new Inscriptions[num];
        numElems = 0;
    }

    public int getNumElems() {
        return numElems;
    }

    public void setNumElems(int numElems) {
        this.numElems = numElems;
    }


    
    
}