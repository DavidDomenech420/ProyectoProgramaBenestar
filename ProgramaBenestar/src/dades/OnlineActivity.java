package dades;
import java.time.LocalDate;

public class OnlineActivity extends Activities {
    private LocalDate startDateActivity;
    private LocalDate finishDateActivity;
    private String linkCourse;
    
    // Constructor
    public OnlineActivity(String activityType, String activityName, String[] collectives, LocalDate startDateInscriptions,
        LocalDate finishDateInscriptions, LocalDate startDateActivity, LocalDate finishDateActivity, String linkCourse) {
        super(activityType, activityName, startDateInscriptions, finishDateInscriptions, collectives, 300); // No hay maximo de instrucciones
        this.startDateActivity = startDateActivity;
        this.finishDateActivity = finishDateActivity;
        this.linkCourse = linkCourse;
    }
    
    // Getters / Setters
    public LocalDate getStartDateActivity() {
        return startDateActivity;
    }

    public void setStartDateActivity(LocalDate startDateActivity) {
        this.startDateActivity = startDateActivity;
    }

    public LocalDate getFinishDateActivity() {
        return finishDateActivity;
    }

    public void setFinishDateActivity(LocalDate finishDateActivity) {
        this.finishDateActivity = finishDateActivity;
    }

    public String getLinkCourse() {
        return linkCourse;
    }

    public void setLinkCourse(String linkCourse) {
        this.linkCourse = linkCourse;
    }

    @Override
    public Activities copia() {
        Activities copia = new OnlineActivity(getActivityType(), getActivityName(), getCollective(), getStartDateInscriptions(), getFinishDateInscriptions(), getStartDateActivity(), getFinishDateActivity(), getLinkCourse());
        return copia;
    }

    @Override
    public String toString(){
        return "Online Activity [Activity Name: " + getActivityName() + ", Start Date Inscriptions: " + getStartDateInscriptions() +
        ", Finish Date Inscriptions: " + getFinishDateInscriptions() + " Collectives: (" + getCollectiveString() + "), Start Date Activity: " + getStartDateActivity() + ", Time to View Activity: " + getFinishDateActivity() + ", Link Course: " + getLinkCourse() + "]";
    }


}
