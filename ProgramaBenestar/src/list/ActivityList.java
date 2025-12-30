package list;

import java.time.LocalDate;

import dades.Activities;
import dades.OneDayActivity;
import dades.OnlineActivity;
import dades.PeriodicActivity;

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

    public void removeActivity(String activityName){
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
            if (list[i].getNumInscriptions() < list[i].getInscriptions().getLenInscriptions()) {
                activitiesList.addActivity(list[i]);
            }
        }
        activitiesName = new String[activitiesList.getNumElems()];
        for (int i = 0; i < activitiesName.length; i++) {
            activitiesName[i] = activitiesList.getActivity(i).getActivityName();
        }
        return activitiesName;
    }

    public ActivityList activitiesFinished(LocalDate usedDate){
        ActivityList finishActivities = new ActivityList(nElem);
        for(int i=0; i< getNumElems(); i++){
            Activities activity = getActivity(i);

            //Comprovem si és activitat d'un dia
            if(activity instanceof OneDayActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                OneDayActivity oneDayAct = (OneDayActivity) activity;
                if(usedDate.isAfter(oneDayAct.getDay())){
                    finishActivities.addActivity(activity);
                }

            //Comprovem si és activitat online
            }else if(activity instanceof OnlineActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                OnlineActivity onlineAct = (OnlineActivity) activity;
                LocalDate finish = onlineAct.getFinishDateActivity();
                if(usedDate.isAfter(finish)){  
                    finishActivities.addActivity(activity);
                }

            //Comprovem si és activitat periòdica
            }else if(activity instanceof PeriodicActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                PeriodicActivity periodicAct = (PeriodicActivity) activity;
                LocalDate start = periodicAct.getInicialDate();
                int weeks = periodicAct.getWeeksOfActivity();
                LocalDate finish = start.plusWeeks(weeks);
                if(usedDate.isAfter(finish)){
                    finishActivities.addActivity(activity);
                }
            }
        }
        return finishActivities;
    }

}
