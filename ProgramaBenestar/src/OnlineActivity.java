import java.util.Date;

public class OnlineActivity {
    private String activityName; // El id de las actividades
    private String collective;
    private Date startDateInscriptions;
    private Date finishDateInscriptions;
    private Date startDateActivity;
    private Date timeToViewActivity;
    
    // Constructor
    public OnlineActivity(String activityName, String collective, Date startDateInscriptions,
        Date finishDateInscriptions, Date startDateActivity, Date timeToViewActivity) {
            this.activityName = activityName;
            this.collective = collective;
            this.startDateInscriptions = startDateInscriptions;
        this.finishDateInscriptions = finishDateInscriptions;
        this.startDateActivity = startDateActivity;
        this.timeToViewActivity = timeToViewActivity;
    }
    
    // Getters / Setters
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getStartDateInscriptions() {
        return startDateInscriptions;
    }

    public void setStartDateInscriptions(Date startDateInscriptions) {
        this.startDateInscriptions = startDateInscriptions;
    }

    public Date getFinishDateInscriptions() {
        return finishDateInscriptions;
    }

    public void setFinishDateInscriptions(Date finishDateInscriptions) {
        this.finishDateInscriptions = finishDateInscriptions;
    }

    public Date getStartDateActivity() {
        return startDateActivity;
    }

    public void setStartDateActivity(Date startDateActivity) {
        this.startDateActivity = startDateActivity;
    }

    public Date getTimeToViewActivity() {
        return timeToViewActivity;
    }

    public void setTimeToViewActivity(Date timeToViewActivity) {
        this.timeToViewActivity = timeToViewActivity;
    }


}
