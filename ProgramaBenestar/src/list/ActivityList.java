package list;

import java.time.LocalDate;

import dades.Activities;

public class ActivityList {
    Activities[] list;
    int nElem;
    
    public ActivityList(int n){
        list = new Activities[n];
        nElem = 0;
    }

    public void addActivity (Activities activity){
        if (nElem < list.length) {
            list[nElem] = activity;
            nElem++;
        }
        else {
            System.out.println("No more space in the list");
        }
    }

    public void deleteActivity(String activityName){
        boolean found = false;
        int i = 0;
        while(i<nElem && !found){
            String name = list[i].getActivityName();
            if(activityName.equalsIgnoreCase(name)){
                list[i] = list[nElem-1];
                list[nElem-1] = null;
                nElem--;
                found = true;
            }
            i++;
        }
    }

    @Override
    public String toString() {
        String aux = "Activity List: \n";
        for (int i = 0; i < nElem; i++) {
            aux += "1 - " + list[i] + "\n";
        }
        return aux;
    }

    public int getNumElems(){
        return nElem;
    }

    public Activities getActivity(int pos){
        return list[pos];
    }
    
    public Activities getActivity(String name){
        Activities activity = null;
        for (int i = 0; i < nElem; i++) {
            if (list[i].getActivityName().equalsIgnoreCase(name)) {
                activity = list[i];
            }
        }

        return activity;
    }

    public ActivityList activitiesInscriptionOpen(LocalDate date){
        ActivityList activitiesInsOpen = new ActivityList(nElem);
        for (int i = 0; i < nElem; i++) {
            if (list[i].getStartDateInscriptions().isBefore(date) && list[i].getFinishDateInscriptions().isAfter(date)) {
                activitiesInsOpen.addActivity(list[i]);
            }
        }
        return activitiesInsOpen;
    }

    public String[] activitiesAvaliablePlaces(){
        String[] activitiesName;
        ActivityList activitiesList = new ActivityList(nElem);
        for (int i = 0; i < nElem; i++) {
            if (list[i].getNumInscriptions() < list[i].getInscriptions().length) {
                activitiesList.addActivity(list[i]);
            }
        }
        activitiesName = new String[activitiesList.getNumElems()];
        for (int i = 0; i < activitiesName.length; i++) {
            activitiesName[i] = activitiesList.getActivity(i).getActivityName();
        }
        return activitiesName;
    }

}
