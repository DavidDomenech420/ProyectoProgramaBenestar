package dades;
import java.time.LocalDate;

import Usuaris.User;
import list.InscriptionList;

public abstract class Activities {
    private String activityType;
    private String activityName;
    private String[] collective = new String[3]; //Max 3 col·lectius
    private int numElemsCollective;
    private LocalDate startDateInscriptions;
    private LocalDate finishDateInscriptions;
    private User[] waitingList = new User[10]; //Max 10 persones a la llista d'espera
    private int numElemsWaitingList;
    private InscriptionList inscriptions;


    // Constructor
    public Activities(String activityType, String activityName, LocalDate startDateInscriptions, LocalDate finishDateInscriptions, String[] collective, int maxInscriptions){
        if (startDateInscriptions.isAfter(finishDateInscriptions)) {
            startDateInscriptions = finishDateInscriptions;
        }
        this.activityType = activityType;
        this.activityName = activityName;
        this.startDateInscriptions = startDateInscriptions;
        this.finishDateInscriptions = finishDateInscriptions;
        this.collective = collective;
        this.numElemsCollective = collective.length;
        this.numElemsWaitingList = 0;
        this.inscriptions = new InscriptionList(maxInscriptions);
    }

    // Getters / Setters
    public String getActivityType() {
        return activityType;
    }


    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }


    public String getActivityName() {
        return activityName;
    }


    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }


    public String[] getCollective() {
        return collective;
    }


    public String getCollectiveString() {
        int i = 0;
        if (collective[0] == null) {
            return "No collectives.";
        }
        String result = collective[i];
        i++;
        while (i < collective.length && collective[i] != null) {
            result += ", " + collective[i];
            i++;
        }
        return result;
    }


    public LocalDate getStartDateInscriptions() {
        return startDateInscriptions;
    }


    public void setStartDateInscriptions(LocalDate startDateInscriptions) {
        if (startDateInscriptions.isAfter(this.finishDateInscriptions)) {
            System.out.println("Start date cannot be after finish date. No changes made.");
            return;
        }
        this.startDateInscriptions = startDateInscriptions;
    }


    public LocalDate getFinishDateInscriptions() {
        return finishDateInscriptions;
    }


    public void setFinishDateInscriptions(LocalDate finishDateInscriptions) {
        if (finishDateInscriptions.isBefore(this.startDateInscriptions)) {
            System.out.println("Finish date cannot be before start date. No changes made.");
            return;
        }
        this.finishDateInscriptions = finishDateInscriptions;
    }


    public User[] getWaitingList() {
        return waitingList;
    }


    public void setWaitingList(User[] waitingList) {
        this.waitingList = waitingList;
        this.numElemsWaitingList = waitingList.length;
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
        char contains = 'N';
        for (int i = 0; i < collective.length; i++) {
            if (member.getUserType().equals(collective[i])) {
                contains = 'Y';
            }
        }
        if (contains == 'N') {
            System.out.println("The member's user type is not allowed for this activity.");
            return;
        }
        if (numElemsWaitingList < waitingList.length) {
            waitingList[numElemsWaitingList] = member;
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

    
    public void printWaitingList(){
        System.out.println("Waiting List:");
        for (int i = 0; i < numElemsWaitingList; i++) {
            System.out.println((i + 1) + ". " + waitingList[i].toString());
        }
    }


    // *** Clases Abstractas ***
    public abstract Activities copia();


}
