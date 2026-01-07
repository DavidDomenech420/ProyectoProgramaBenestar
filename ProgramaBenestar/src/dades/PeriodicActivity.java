package dades;
import java.time.LocalDate;   //Júlia Alquézar Duran and Sandra Serra Férriz
import java.time.LocalTime;

/**
 * @author Autoria de la classe: Júlia Alquézar Duran i Sandra Serra Férriz
 */
public class PeriodicActivity extends Activities{
    private String dayOfActivity; //día on es fan les activitats
    private LocalTime inicialTime;
    private LocalTime finalTime;
    private LocalDate inicialDate; //Data inicial activitat
    private int weeksOfActivity; //número de setmanes consecutives en que es fará l'activitat
    private double priceActivity; //preu per tota l'activitat
    private String centerName; //nom del centre
    private String cityName;   //nom de la ciutat
   

    //---------- CONSTRUCTOR ----------
    public PeriodicActivity(String activityType, String activityName, LocalDate startDateInscriptions, LocalDate finishDateInscriptions, String[] collective, int maxInscriptions, String dayOfActivity, LocalTime inicialTime, LocalTime finalTime,
            LocalDate inicialDate, int weeksOfActivity, double priceActivity, String centerName,
            String cityName) {
        super(activityType, activityName, startDateInscriptions, finishDateInscriptions, collective, maxInscriptions); //constructor pare 
        this.dayOfActivity = dayOfActivity;
        this.inicialTime = inicialTime;
        this.finalTime = finalTime;
        this.inicialDate = inicialDate;
        this.weeksOfActivity = weeksOfActivity;
        this.priceActivity = priceActivity;
        this.centerName = centerName;
        this.cityName = cityName;
    }

    //------------ GETTERS I SETTERS ------------
    

    public String getDayOfActivity() {
        return dayOfActivity;
    }


    public void setDayOfActivity(String dayOfActivity) {
        this.dayOfActivity = dayOfActivity;
    }


    public LocalTime getInicialTime() {
        return inicialTime;
    }


    public void setInicialTime(LocalTime inicialTime) {
        this.inicialTime = inicialTime;
    }


    public LocalTime getFinalTime() {
        return finalTime;
    }


    public void setFinalTime(LocalTime finalTime) {
        this.finalTime = finalTime;
    }


    public LocalDate getInicialDate() {
        return inicialDate;
    }


    public void setInicialDate(LocalDate inicialDate) {
        this.inicialDate = inicialDate;
    }


    public int getWeeksOfActivity() {
        return weeksOfActivity;
    }


    public void setWeeksOfActivity(int weeksOfActivity) {
        this.weeksOfActivity = weeksOfActivity;
    }


    public double getPriceActivity() {
        return priceActivity;
    }


    public void setPriceActivity(double priceActivity) {
        this.priceActivity = priceActivity;
    }


    public String getCenterName() {
        return centerName;
    }


    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }


    public String getCityName() {
        return cityName;
    }


    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public Activities copia() {
        Activities copia = new PeriodicActivity(getActivityType(), getActivityName(), getStartDateInscriptions(), getFinishDateInscriptions(), getCollective(), getNumInscriptions(), getDayOfActivity(), getInicialTime(), getFinalTime(), getInicialDate(), getWeeksOfActivity(), getPriceActivity(), getCenterName(), getCityName());
        return copia;
    }

    //------------ TO STRING ------------
   @Override
    public String toString(){
        return "Periodic [Activity Name: " + getActivityName() + ", Start Date Inscriptions: " + getStartDateInscriptions() +
        ", Finish Date Inscriptions: " + getFinishDateInscriptions() + " Collectives: (" + getCollectiveString() + ") Day of activity: "  + getDayOfActivity() + " Inicial time: " + getInicialTime() + " Final time: " + getFinalTime() + " Inicial date: " + getInicialDate() + " Weeks of activity: " + getWeeksOfActivity() + " Price of activity: " + getPriceActivity() + " Center name: " + getCenterName() + " City name: " + getCityName();
    }
}