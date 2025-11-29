package dades;
import java.time.LocalDate;

public class OneDayActivity extends Activities{
    private String city;
    

    public OneDayActivity(String activityName, LocalDate startDateInscriptions,
            LocalDate finishDateInscriptions,String[] collectives, int limitPlaces, String city){
        super(activityName, startDateInscriptions, finishDateInscriptions, collectives, limitPlaces);
        this.city = city;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString(){
        return "One Day Activity [Activity Name: " + getActivityName() + ", Start Date Inscriptions: " + getStartDateInscriptions() +
        ", Finish Date Inscriptions: " + getFinishDateInscriptions() + " Collectives: (" + getCollectiveString() + "), City: " + city + "]";
    }
}
