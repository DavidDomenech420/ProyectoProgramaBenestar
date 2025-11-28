package dades;
import java.util.Date;   //Júlia Alquézar Duran and Sandra Serra Férriz
import java.time.LocalTime;

public class PeriodicActivity {
    private String idActivity; //nom de l'activitat. La identifica: no n'hi haurà dues iguals!
    private String dayOfActivity; //día on es fan les activitats
    private LocalTime inicialTime;
    private LocalTime finalTime;
    private Date inicialDate; //Data inicial activitat
    private int weeksOfActivity; //número de setmanes consecutives en que es fará l'activitat
    private int limitPlace; //places limitades
    private double priceActivity; //preu per tota l'activitat
    private String centerName; //nom del centre
    private String cityName;   //nom de la ciutat
    private String[] collective; //taula de col·lectius*
    private int numInscriptions; //num inscripcions actuals
    private int actualLimit; //límit actual de l'activitat*
    private Date inicialDateInscription;
    private Date finalDateIncription; //la data de les inscripcions ha de ser abans que la de les activitats!
    
    //*col·lectius --> és taula? Ja que hem pensat en guardar un col·lectiu en cada posició d'aquesta.
    //*inscripcions --> encara que tinguem límit o no, necessitem saber les inscripcions actuals?
    //*Fer package de cada classe de dades!
    //*Fer classe usuari

    //---------- CONSTRUCTOR ----------
    public PeriodicActivity(String idActivity, String dayOfActivity, LocalTime inicialTime, LocalTime finalTime,
            Date inicialDate, int weeksOfActivity, int limitPlace, double priceActivity, String centerName,
            String cityName, String[] collective, Date inicialDateInscription,
            Date finalDateIncription) {
        this.idActivity = idActivity;
        this.dayOfActivity = dayOfActivity;
        this.inicialTime = inicialTime;
        this.finalTime = finalTime;
        this.inicialDate = inicialDate;
        this.weeksOfActivity = weeksOfActivity;
        this.limitPlace = limitPlace;
        this.priceActivity = priceActivity;
        this.centerName = centerName;
        this.cityName = cityName;
        this.collective = collective;
        this.numInscriptions = 0; //quan es crea una activitat, no hi ha ningú inscrit.
        this.inicialDateInscription = inicialDateInscription;
        this.finalDateIncription = finalDateIncription;
    }
    //---------------------------------

    //------------ GETTERS ------------
    public String getIdActivity() {
        return idActivity;
    }

    public String getDayOfActivity() {
        return dayOfActivity;
    }

    public LocalTime getInicialTime() {
        return inicialTime;
    }

    public LocalTime getFinalTime() {
        return finalTime;
    }

    public Date getInicialDate() {
        return inicialDate;
    }

    public int getWeeksOfActivity() {
        return weeksOfActivity;
    }

    public int getLimitPlace() {
        return limitPlace;
    }

    public double getPriceActivity() {
        return priceActivity;
    }

    public String getCenterName() {
        return centerName;
    }

    public String getCityName() {
        return cityName;
    }

    public String[] getCollective() {
        return collective;
    }

    public int getNumInscriptions() {
        return numInscriptions;
    }

    public int getActualLimit() {
        return actualLimit;
    }

    public Date getInicialDateInscription() {
        return inicialDateInscription;
    }

     public Date getFinalDateIncription() {
        return finalDateIncription;
    }
    //---------------------------------


    //------------ SETTERS ------------
    public void setIdActivity(String idActivity) {
        this.idActivity = idActivity;
    }

    public void setDayOfActivity(String dayOfActivity) {
        this.dayOfActivity = dayOfActivity;
    }

    public void setInicialTime(LocalTime inicialTime) {
        this.inicialTime = inicialTime;
    }

    public void setFinalTime(LocalTime finalTime) {
        this.finalTime = finalTime;
    }

    public void setInicialDate(Date inicialDate) {
        this.inicialDate = inicialDate;
    }

    public void setWeeksOfActivity(int weeksOfActivity) {
        this.weeksOfActivity = weeksOfActivity;
    }

    public void setLimitPlace(int limitPlace) {
        this.limitPlace = limitPlace;
    }

    public void setPriceActivity(double priceActivity) {
        this.priceActivity = priceActivity;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCollective(String[] collective) {
        this.collective = collective;
    }

    public void setNumInscriptions(int numInscriptions) {
        this.numInscriptions = numInscriptions;
    }

    public void setActualLimit(int actualLimit) {
        this.actualLimit = actualLimit;
    }

    public void setInicialDateInscription(Date inicialDateInscription) {
        this.inicialDateInscription = inicialDateInscription;
    }

    public void setFinalDateIncription(Date finalDateIncription) {
        this.finalDateIncription = finalDateIncription;
    }
    //---------------------------------

    
    //----------------- MÈTODE COPIA -------------------
    public PeriodicActivity copia(){
        PeriodicActivity copia = new PeriodicActivity(idActivity, dayOfActivity, inicialTime, finalTime, inicialDate, weeksOfActivity, limitPlace, priceActivity, centerName, cityName, collective, numInscriptions, actualLimit, inicialDateInscription, finalDateIncription);
        return copia;
    
    }
}



