import java.time.LocalDate;

import Usuaris.User;

public class Activities {
    private String activityName;
    private String[] collective;
    private int numElemsCollective;
    private LocalDate startDateInscriptions;
    private LocalDate finishDateInscriptions;
    private User[] waitingList = new User[10]; //Max 10 persones a la llista d'espera
    private int numElemsWaitingList;

    // Constructor
    public Activities(String activityName, int collective_len, LocalDate startDateInscriptions,
        LocalDate finishDateInscriptions) {
        this.activityName = activityName;
        this.collective = new String[collective_len];
        this.startDateInscriptions = startDateInscriptions;
        this.finishDateInscriptions = finishDateInscriptions;
        this.numElemsCollective = 0;
        this.numElemsWaitingList = 0;
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
    public User[] getWaitingList() {
        return waitingList;
    }
    public void setWaitingList(User[] waitingList) {
        this.waitingList = waitingList;
    }

    public void addCollective(String collective){
        if (numElemsCollective < this.collective.length) {
            this.collective[numElemsCollective] = collective;
            numElemsCollective++;
        }
        else {
            System.out.println("Collective is full. Cannot add more collectives.");
        }
    }

    public void addToWaitingList(User member){
        if (numElemsWaitingList < waitingList.length) {
            waitingList[numElemsWaitingList] = member.copy();
            numElemsWaitingList++;
        }
        else {
            System.out.println("Waiting list is full. Cannot add more members.");
        }
    }

    public void removeFromWaitingList(User member){
        for (int i = 0; i < numElemsWaitingList; i++) {
            if (waitingList[i].getNickname().equals(member.getNickname())) {
                // Movem els elements cap a l'esquerra
                for (int j = i; j < numElemsWaitingList - 1; j++) {
                    waitingList[j] = waitingList[j + 1];
                }
                waitingList[numElemsWaitingList - 1] = null;
                numElemsWaitingList--;
                return;
            }
        }
        System.out.println("Member not found in waiting list.");
    }
}
