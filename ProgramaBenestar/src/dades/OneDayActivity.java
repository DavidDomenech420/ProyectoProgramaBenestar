package dades;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Autoria de la classe: Biel Sanz López-Hervás 
 */
public class OneDayActivity extends Activities{
    private String city;
    private LocalDate day;
    private LocalTime startTime;
    private LocalTime finishTime;
    private double price;
    

    public OneDayActivity(String activityType, String activityName, LocalDate startDateInscriptions,
            LocalDate finishDateInscriptions,String[] collectives, int limitPlaces, String city, LocalDate day, LocalTime startTime, LocalTime finishTime, double price) {
        super(activityType, activityName, startDateInscriptions, finishDateInscriptions, collectives, limitPlaces);
        if (startTime.isAfter(finishTime)) {
            startTime = finishTime.minusMinutes(30);
        }
        this.city = city;
        this.day = day;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.price = price;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public LocalDate getDay() {
        return day;
    }
    public void setDay(LocalDate day) {
        this.day = day;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        if (startTime.isAfter(this.finishTime)) {
            System.out.println("La hora d'inici no pot ser posterior a la hora de finalització. No s'han fet canvis.");
            return;
        }
        this.startTime = startTime;
    }
    public LocalTime getFinishTime() {
        return finishTime;
    }
    public void setFinishTime(LocalTime finishTime) {
        if (finishTime.isBefore(this.startTime)) {
            System.out.println("La hora de finalització no pot ser anterior a la hora d'inici. No s'han fet canvis.");
            return;
        }
        this.finishTime = finishTime;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public Activities copia() {
        Activities copia = new OneDayActivity(getActivityType(),getActivityName(), getStartDateInscriptions(), getFinishDateInscriptions(), getCollective(), getNumInscriptions(), getCity(), getDay(), getStartTime(), getFinishTime(), getPrice());
        return copia;
    }

    @Override
    public String toString(){
        return "One Day Activity [Activity Name: " + getActivityName() + ", Start Date Inscriptions: " + getStartDateInscriptions() +
        ", Finish Date Inscriptions: " + getFinishDateInscriptions() + " Collectives: (" + getCollectiveString() + "), City: " + city + "]";
    }
}
