package dades;
public class PeriodicActivityList {
    private PeriodicActivity[] list;
    private int numElem;


    //---------- CONSTRUCTOR ----------
    public PeriodicActivityList(PeriodicActivity[] list, int numElem) {
        this.list = list;
        this.numElem = numElem;
    }
    //---------------------------------


    //------------ GETTERS ------------
    public PeriodicActivity[] getList() {
        return list;
    }

    public int getNumElem() {
        return numElem;
    }
    //---------------------------------


    //------------ SETTERS ------------
    public void setList(PeriodicActivity[] list) {
        this.list = list;
    }

    public void setNumElem(int numElem) {
        this.numElem = numElem;
    }
    //---------------------------------


    //----------------- MÈTODE AFEGIR -------------------
    public void afegirActivity(PeriodicActivity newActivity){
        if(numElem < list.length){
            list[numElem] = newActivity.copia();
            numElem++;
        }
    }
    //---------------------------------------------------


    //----------------- MÈTODE COPIA -------------------
    public PeriodicActivityList copia(){
        PeriodicActivityList newList = new PeriodicActivityList(numElem);
        for(int i=0; i<numElem; i++){
            newList.afegir(list[i]);
        }
        return newList;
    }
    //---------------------------------------------------
    


    


    

    
}
