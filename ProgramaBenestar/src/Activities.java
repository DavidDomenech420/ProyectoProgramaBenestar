import java.time.LocalDate;

public class Activities {
    private String activityName;
    private String[] collective;
    private int numElems;
    private LocalDate startDateInscriptions;
    private LocalDate finishDateInscriptions;

    // Constructor
    public Activities(String activityName, int collective_len, LocalDate startDateInscriptions,
        LocalDate finishDateInscriptions) {
        this.activityName = activityName;
        this.collective = new String[collective_len];
        this.startDateInscriptions = startDateInscriptions;
        this.finishDateInscriptions = finishDateInscriptions;
        this.numElems = 0;
    }

    // Getters / Setters
    public String getActivityName() {
        return activityName;
    }
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
    public String[] getCollective() {
        return collective;
    }
    public void setCollective(String[] collective) {
        this.collective = collective;
    }
    public LocalDate getStartDateInscriptions() {
        return startDateInscriptions;
    }
    public void setStartDateInscriptions(LocalDate startDateInscriptions) {
        this.startDateInscriptions = startDateInscriptions;
    }
    public LocalDate getFinishDateInscriptions() {
        return finishDateInscriptions;
    }
    public void setFinishDateInscriptions(LocalDate finishDateInscriptions) {
        this.finishDateInscriptions = finishDateInscriptions;
    }

    public void addCollectiveMember(String member){
        if (numElems < collective.length) {
            collective[numElems] = member;
            numElems++;
        }
        else {
            System.out.println("Collective is full. Cannot add more members.");
        }
    }
}
