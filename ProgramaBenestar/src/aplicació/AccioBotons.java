package aplicació;

import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.*;

import dades.Activities;
import dades.OneDayActivity;
import dades.OnlineActivity;
import dades.PeriodicActivity;
import list.ActivityList;
import list.InscriptionList;
import list.UserList;
import Usuaris.User;
import Usuaris.PDIUser;
import Usuaris.PTGASUser;
import Usuaris.StudentUser;

//Aquesta classe és la responsable de tractar els esdeveniments dels botons.
public class AccioBotons implements ActionListener {
    private ActivityList activities;
    private UserList users;

    //Si no es crea el constructor, la classe no té accés a les dades
    public AccioBotons (ActivityList activities, UserList users){
        this.activities = activities;
        this.users = users;
    }

    public void actionPerformed (ActionEvent event) {
        String option = event.getActionCommand(); //S'ha d'utilitzar getActionCommand per saber quina opció s'ha escollit

        //----- OPCIÓ 1 -----
        if(option.equalsIgnoreCase("Informació sobre la data actual")){
            JOptionPane.showMessageDialog(null, "Data actual al programa: " +AppProgramaBenestar.usedDate, "DATA DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);

            int confirmation = JOptionPane.showConfirmDialog(null, "Vol consultar una altra data?", "CONFIRMACIÓ", JOptionPane.YES_NO_OPTION);
            if(confirmation == JOptionPane.YES_OPTION){
                String newDateString = JOptionPane.showInputDialog(null, "Introdueix una data (AAAA-MM-DD): ", "DATA DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                //Comprovem que no hagi cancel·lat
                if(newDateString != null){
                    try{
                        AppProgramaBenestar.usedDate = LocalDate.parse(newDateString);
                        JOptionPane.showMessageDialog(null, "Data actualitzada: " +AppProgramaBenestar.usedDate, "DATA DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception e){
                        JOptionPane.showMessageDialog(null, "Error en el format de la data", "ERROR!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
        //-------------------


        //----- OPCIÓ 2 -----
        else if (option.equalsIgnoreCase("Informació de les dades d'una llista")){
            //Demanem l'opció a mostrar per pantalla
            String answer = JOptionPane.showInputDialog(null, "Escriu de què vols obtenir la informació (usuaris/activitats): ", "Informació de llista", JOptionPane.INFORMATION_MESSAGE);
            String phrase = "";
            String information = "";

            //Si l'usuari ha cancelat l'acció, la resposta serà null
            if(answer != null){
                
                //----- SI L'OPCIÓ HA SIGIT USUARIS -----
                if(answer.equalsIgnoreCase("usuaris")){
                    if(users.getNumElems() == 0){
                        JOptionPane.showMessageDialog(null, "No hi ha informació d'usuaris", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                    
                    } else if (users.getNumElems() > 0){
                        try{
                            String answer2 = JOptionPane.showInputDialog(null, "Escriu quin tipus d'usuari vols que es mosti:\n1: TOTS els usuaris\n2: professorat (PDI)\n3: personal tècnic i de gestió (PTGAS)\n4: estudiants\nEscriu la teva opció a continuació: ", "Tipus d'informació", JOptionPane.INFORMATION_MESSAGE);
                            
                            //Si l'usuari ha cancelat l'acció, la resposta serà null
                            if(answer2 != null){
                                int userType = Integer.parseInt(answer2); //conversió a enter

                                if(users.getNumElems() == 0){
                                    JOptionPane.showMessageDialog(null, "No hi ha informació d'usuaris", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);

                                } else if (users.getNumElems() > 0){
                                    //Opció 1 - Mostrar informació de tots els usuaris
                                    if(userType == 1){
                                        information = "TOTS ELS USUARIS";
                                        phrase = "Has escollit que es mostri la informació de tots els usuaris: ";
                                        for(int i=0; i<users.getNumElems(); i++){
                                            phrase = phrase + users.getUser(i).toString()+"\n";
                                        }
                                    }
                                    //Opció 2 - Mostrar informació del professorat (PDI)
                                    if(userType == 2){
                                        information = "TOTS ELS PROFESSORS";
                                        phrase = "Has escollit que es mostri la informació del professorat (PDI): ";
                                        for(int i=0; i<users.getNumElems(); i++){
                                            if(users.getUser(i).getUserType().equalsIgnoreCase("PDI")){
                                                phrase = phrase + users.getUser(i).toString()+"\n";
                                            }
                                        }
                                    }
                                    //Opció 3 - Mostrar informació del personal tècnic i de gestió (PTGAS)
                                    if(userType == 3){
                                        information = "TOTS EL PERSONAL TÈCNIC I DE GESTIÓ";
                                        phrase = "Has escollit que es mostri la informació del personal tècnic i de gestió (PTGAS): ";
                                        for(int i=0; i<users.getNumElems(); i++){
                                            if(users.getUser(i).getUserType().equalsIgnoreCase("PTGAS")){
                                                phrase = phrase + users.getUser(i).toString()+"\n";
                                            }
                                        }
                                    }
                                    //Opció 4 - Mostrar informació dels estudiants
                                    if(userType == 4){
                                        information = "TOTS ELS ESTUDIANTS";
                                        phrase = "Has escollit que es mostri la informació dels estudiants: ";
                                        for(int i=0; i<users.getNumElems(); i++){
                                            if(users.getUser(i).getUserType().equalsIgnoreCase("Student")){
                                                phrase = phrase + users.getUser(i).toString()+"\n";
                                            }
                                        }
                                    }
                                    JOptionPane.showMessageDialog(null, phrase, information, JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        } catch (NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "Error: Has d'introduir un número vàlid", "ERROR!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    


                //----- Si L'OPCIÓ HA SIGUT ACTIVITATS -----
                } else if (answer.equalsIgnoreCase("activitats")){
                    if(activities.getNumElems() == 0){
                        JOptionPane.showMessageDialog(null, "No hi ha informació d'activitats", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                    
                    } else if (activities.getNumElems() > 0){
                        try{
                            String answer3 = JOptionPane.showInputDialog(null, "Escriu quin tipus d'activitat vols que es mosti:\n1: TOTES les activitats\n2: Activitats d'un dia\n3: Activitats periòdiques\n4: Activitats Online\nEscriu la teva opció a continuació: ", "Tipus d'informació", JOptionPane.INFORMATION_MESSAGE);
                            String phrase2 = "";

                            //Si l'usuari ha cancelat l'acció, la resposta serà null
                            if(answer3 != null){
                                int activityType = Integer.parseInt(answer3); //conversió a enter

                                //Opció 1 - Mostrar la informació de totes les activitats
                                if(activityType == 1){
                                    information = "TOTS LES ACTIVITATS";
                                    phrase2 = "Has escollit que es mostri la informació de totes les activitats: ";
                                    for(int i=0; i<activities.getNumElems(); i++){
                                        phrase2 = phrase2 + activities.getActivity(i);
                                    }
                                }
                                //Opció 2 - Mostrar la informació de les activitats d'un dia
                                if(activityType == 2){
                                    information = "ACTIVITATS D'UN DIA";
                                    phrase2 = "Has escollit que es mostri la informació de les activitats d'un dia: ";
                                    for(int i=0; i<activities.getNumElems(); i++){
                                        if(activities.getActivity(i).getActivityType().equalsIgnoreCase("OneDay")){
                                            phrase2 = phrase2 + activities.getActivity(i);
                                        }
                                    }
                                }
                                //Opció 3 - Mostrar la informació de les activitats periòdiques
                                if(activityType == 3){
                                    information = "ACTIVITATS PERIÒDIQUES";
                                    phrase2 = "Has escollit que es mostri la informació de les activitats periòdiques: ";
                                    for(int i=0; i<activities.getNumElems(); i++){
                                        if(activities.getActivity(i).getActivityType().equalsIgnoreCase("Periodic")){
                                            phrase2 = phrase2 + activities.getActivity(i);
                                        }
                                    }
                                }
                                //Opció 4 - Mostrar la informació de les activitats en línia
                                if(activityType == 4){
                                    information = "ACTIVITATS ONLINE";
                                    phrase2 = "Has escollit que es mostri la informació de les activitats Online: ";
                                    for(int i=0; i<activities.getNumElems(); i++){
                                        if(activities.getActivity(i).getActivityType().equalsIgnoreCase("Online")){
                                            phrase2 = phrase2 + activities.getActivity(i);
                                        }
                                    }
                                }
                                JOptionPane.showMessageDialog(null, phrase, information, JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Error: Has d'introduir un número vàlid", "ERROR!", JOptionPane.WARNING_MESSAGE);
                        }
                    }                    
                }
            }
        }
        //-------------------


        //----- OPCIÓ 3 -----
        else if (option.equalsIgnoreCase("Informació de les activitats en període d'inscripció")){
            LocalDate today = LocalDate.now();
            ActivityList openInscriptionActivities = activities.activitiesInscriptionOpen(today);

            if(openInscriptionActivities.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi ha activitats en període d'inscripció", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);

            } else if (openInscriptionActivities.getNumElems() > 0){
                String phrase = "Activitats en període d'inscripció: \n";

                for (int i = 0; i < openInscriptionActivities.getNumElems(); i++) {
                    int numPlazasDisp = openInscriptionActivities.getActivity(i).getInscriptions().getLenInscriptions() - openInscriptionActivities.getActivity(i).getNumInscriptions();
                    phrase = phrase + (i+1) + "- " + openInscriptionActivities.getActivity(i).getActivityName() + ", té " + numPlazasDisp + " places disponibles";
                }
                JOptionPane.showMessageDialog(null, phrase, "En període d'inscripció", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //-------------------


        //----- OPCIÓ 4 -----
        else if (option.equalsIgnoreCase("Informació d'activitats amb classe en la data actual")){
            if(activities.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi ha activitats amb classe en la data actual", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);

            } else if (activities.getNumElems() > 0){
                String phrase = "Activitats amb classe en la data actual: \n";

                for (int i = 0; i < activities.getNumElems(); i++){

                    if (activities.getActivity(i) instanceof OneDayActivity){
                        OneDayActivity oneDay = (OneDayActivity) activities.getActivity(i);
                        if (oneDay.getDay().isEqual(AppProgramaBenestar.usedDate)){
                            phrase = phrase + oneDay.toString()+ "\n";
                        }
                    }
                    else if (activities.getActivity(i) instanceof OnlineActivity){
                        OnlineActivity online = (OnlineActivity) activities.getActivity(i);
                        if (online.getStartDateActivity().isBefore(AppProgramaBenestar.usedDate) && online.getFinishDateActivity().isAfter(AppProgramaBenestar.usedDate)){
                            phrase = phrase + online.toString()+ "\n";
                        }
                    }
                    else if(activities.getActivity(i) instanceof PeriodicActivity){
                        PeriodicActivity periodic = (PeriodicActivity) activities.getActivity(i);
                        if (periodic.getInicialDate().isBefore(AppProgramaBenestar.usedDate) && periodic.getInicialDate().plusDays((long) (periodic.getWeeksOfActivity()*7)).isAfter(AppProgramaBenestar.usedDate)){
                            phrase = phrase + periodic.toString()+ "\n";
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, phrase, "Amb classe en la data actual", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //-------------------


        //----- OPCIÓ 5 -----
        else if (option.equalsIgnoreCase("Activitats actives en la data actual")){
            String message = "Activitats actives en la data actual ("+AppProgramaBenestar.usedDate+"): \n";

            if(activities.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi ha activitats actives en la data actual", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            
            } else if (activities.getNumElems() > 0){
                for(int i=0; i<activities.getNumElems(); i++){
                    Activities activity = activities.getActivity(i);

                    //Comprovació per activitats d'un dia
                    if(activity instanceof OneDayActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                        OneDayActivity oneDayAct = (OneDayActivity) activity;
                        if(oneDayAct.getDay().isEqual(AppProgramaBenestar.usedDate)){
                            message = message + oneDayAct.getActivityName()+ "(D'un dia)\n";
                        }
                    //Comprovació per activitats online
                    } else if(activity instanceof OnlineActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                        OnlineActivity onlineAct = (OnlineActivity) activity;
                        LocalDate start = onlineAct.getStartDateActivity();
                        LocalDate finish = onlineAct.getFinishDateActivity();
                        if(!AppProgramaBenestar.usedDate.isBefore(start) && !AppProgramaBenestar.usedDate.isAfter(finish)){ //Si posem 'usedDate.isAfter(start) && usedDate.isBefore(finish)', exclou els extrems
                            message = message + onlineAct.getActivityName()+ "(Online)\n";
                        }

                    //Comprovació per activitats periòdiques
                    } else if(activity instanceof PeriodicActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                        PeriodicActivity periodicAct = (PeriodicActivity) activity;
                        LocalDate start = periodicAct.getInicialDate();
                        int weeks = periodicAct.getWeeksOfActivity();
                        LocalDate finish = start.plusWeeks(weeks);
                        if(!AppProgramaBenestar.usedDate.isBefore(start) && !AppProgramaBenestar.usedDate.isAfter(finish)){
                            message = message + periodicAct.getActivityName()+ "(Periòdica)";
                            
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, message, "Activitats actives", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //-------------------


        //----- OPCIÓ 6 -----
        else if (option.equalsIgnoreCase("Activitats amb places disponibles")){
            if(activities.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi ha activitats amb places disponibles", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);

            } else if (activities.getNumElems() > 0){
                String phrase = "Activitats amb places disponibles: \n";

                for(int i = 0; i < activities.getNumElems(); i++){
                    if(activities.getActivity(i).getNumInscriptions() < activities.getActivity(i).getInscriptions().getLenInscriptions()){
                        int remaining = activities.getActivity(i).getInscriptions().getLenInscriptions() - activities.getActivity(i).getNumInscriptions();
                        phrase = phrase + activities.getActivity(i)+ " --> Places disponibles: "+remaining;
                    }
                }
                JOptionPane.showMessageDialog(null, phrase, "Activitats amb places", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //-------------------


        //----- OPCIÓ 7 -----
        else if (option.equalsIgnoreCase("Informació d'una activitat")){
            if(activities.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi ha activitats registrades", "ATENCIÓ!", JOptionPane.INFORMATION_MESSAGE);
            
            } else if (activities.getNumElems() > 0){
                //Demanem el nom de l'activitat a mostrar per pantalla
                String activityName = JOptionPane.showInputDialog("Indica el nom de l'activitat");
                if(activityName != null){
                    if(activities.getActivity(activityName) != null){
                        JOptionPane.showMessageDialog(null, activities.getActivity(activityName), "Activitat: "+activityName, JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No existeix cap activitat amb aquest nom!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
        //-------------------


        //----- OPCIÓ 8 -----
        else if (option.equalsIgnoreCase("Informació d'un usuari")){
            if(users.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi ha usuaris registrats!", "ATENCIÓ!", JOptionPane.INFORMATION_MESSAGE);
            
            } else if (users.getNumElems() > 0){
                String userName = JOptionPane.showInputDialog("Escriu el nom de l'usuari: ");
                if(userName != null){
                    if(users.getUser(userName) != null){
                        JOptionPane.showMessageDialog(null, users.getUser(userName), "Nom de l'usuari: "+userName, JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No existeix cap usuari amb aquest nom!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
        //-------------------


        //----- OPCIÓ 9 -----
        else if (option.equalsIgnoreCase("Activitats on estàs inscrit")){
            if(activities.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi han activitats registrades!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            
            } else if (activities.getNumElems() > 0){
                String userName = JOptionPane.showInputDialog("Escriu el nom de l'usuari: ");
                String phrase = "";
                if(userName != null){
                    if(users.getUser(userName) != null){
                        phrase = "Activitats on estàs inscrit: \n";

                        for(int i=0; i<activities.getNumElems(); i++){
                            Activities activity = activities.getActivity(i);
                            InscriptionList inscriptedUsers = activity.getInscriptions();
                            
                            for(int j=0; j<inscriptedUsers.getNumElems(); j++){
                                if(((inscriptedUsers.getInscription(j)).getNickName()).equalsIgnoreCase(userName)){
                                    phrase = phrase + "- " +activity.getActivityName();
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(null, phrase, "Activitats on està inscrit",  JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "No existeix cap usuari amb aquest nom!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                    }
                }   
            }
        }
        //-------------------


        //----- OPCIÓ 10 -----
        else if (option.equalsIgnoreCase("Inscripció a una activitat")){
            if(activities.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi han activitats registrades!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                LocalDate today = LocalDate.now();
                ActivityList openInscriptionActivities = activities.activitiesInscriptionOpen(today);
                String phrase = "Aquestes son les activitats disponibles actualment:";
                for (int i = 0; i < openInscriptionActivities.getNumElems(); i++) {
                    int numPlazasDisp = openInscriptionActivities.getActivity(i).getInscriptions().getLenInscriptions() - openInscriptionActivities.getActivity(i).getNumInscriptions();
                    phrase = phrase + (i+1) + "- " + openInscriptionActivities.getActivity(i).getActivityName() + ", te " + numPlazasDisp + " places disponibles";
                }
                JOptionPane.showMessageDialog(null, phrase, "Inscriure's a una activitat",  JOptionPane.INFORMATION_MESSAGE);
                
                String activityChosen = JOptionPane.showInputDialog("Escriu l'activitat en la qual et vols inscriure: ");
                boolean trobat = false;
                int i = 0;
                Activities activity = null;
                User newUser = null;
                int posUser = 0;
                while(!trobat && i < activities.getNumElems()){
                    if(activityChosen.equalsIgnoreCase(activities.getActivity(i).getActivityName())){
                        trobat = true;
                        activity = activities.getActivity(i);
                    }
                    i++;
                }
                if(!trobat){
                    JOptionPane.showMessageDialog(null, "Ho sentim. L'activitat que has triat, no existeix o està fora de termini.", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            
                }
                else{ //Ara controlem si l'usuari ja està registrat
                    String nickname = JOptionPane.showInputDialog("Perfecte! Quin és el teu nom?: ");
                    i = 0;
                    trobat = false;
                    while(!trobat && i < users.getNumElems()){
                        if(nickname.equalsIgnoreCase(users.getUser(i).getNickname())){
                            trobat = true;
                            posUser = i;
                            if(!activity.getCollectiveString().contains(users.getUser(i).getUserType())){ //Mirem si pertany al grup correcte per a fer l'activitat
                                JOptionPane.showMessageDialog(null, "Ho sentim. No pertanys al grup correcte per a inscriure't en aquesta activitat.", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        i++;
                    }
                    if(!trobat){ // AFEGIR NOU USUARI
                        JOptionPane.showMessageDialog(null, "No estàs registrat com a usuari. A continuació se't faran una sèrie de preguntes per a registrar-te.", "Nou usuari",  JOptionPane.INFORMATION_MESSAGE);
                        String stringCollective = JOptionPane.showInputDialog("A quin grup pertanys? \n[1] PDI\n[2] PTGAS\n[3] Estudiant\nEscriu el número a continuació: ");
                        int numCollective = Integer.parseInt(stringCollective);
                        
                        // Col·lectiu:
                        while(numCollective != 1 && numCollective != 2 && numCollective != 3){
                            stringCollective = JOptionPane.showInputDialog("No has introduit un número correcte. Torna a intentar-ho: ");
                            numCollective = Integer.parseInt(stringCollective);
                        }
                        String collective = null;
                        if(numCollective == 1){
                            collective = "PDI";
                        }
                        else if(numCollective == 2){
                            collective = "PTGAS";
                        }
                        else{
                            collective = "Student";
                        }

                        //Mirem si està permès que el tipus d'usuari faci l'activitat:
                        boolean isPermited = !false;
                        i = 0;
                        while(!isPermited && i < activity.getLenCollective()){
                            if(collective.equalsIgnoreCase(activity.getCollective()[i])){
                                isPermited = true;
                            }
                        }
                        if(!isPermited){
                            JOptionPane.showMessageDialog(null, "Ho sentim. El teu grup d'usuaris no pot realitzar aquesta activitat.", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        //E-Mail:
                        String email = JOptionPane.showInputDialog("Quin és el teu e-mail (sense @)? Escriu-lo a continuació: ");

                        //Si és usuari PDI:
                        if(collective.equalsIgnoreCase("PDI")){
                            String campus = JOptionPane.showInputDialog("En quin campus estàs?: ");
                            String department = JOptionPane.showInputDialog("Quin és el teu departament?: ");
                            newUser = new PDIUser(collective, nickname, email, campus, department);
                            users.addUser(newUser);
                        }
                        else if(collective.equalsIgnoreCase("PTGAS")){
                            String campus = JOptionPane.showInputDialog("En quin campus estàs?: ");
                            newUser = new PTGASUser(collective, nickname, email, campus);
                            users.addUser(newUser);
                        }
                        else{
                            String degree = JOptionPane.showInputDialog("En quin grau estàs?: ");
                            String syear = JOptionPane.showInputDialog("Quin va ser l'any en el qual vas començar el grau?: ");
                            int year = Integer.parseInt(syear);
                            newUser = new StudentUser(collective, nickname, email, degree, year);
                            users.addUser(newUser);
                        }
                    } 
                    else{
                        newUser = users.getUser(posUser); //L'usuari ja estava registrat.

                        // Mirem si l'usuari pot fer l'activitat segons el seu tipus:
                        boolean isPermited = false;
                        i = 0;
                        while(!isPermited && i < activity.getLenCollective()){
                            if(newUser.getUserType().equalsIgnoreCase(activity.getCollective()[i])){
                                isPermited = true;
                            }
                        }
                        if(!isPermited){
                            JOptionPane.showMessageDialog(null, "Ho sentim. El teu grup d'usuaris no pot realitzar aquesta activitat.", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                        }
                        
                    }

                    //Afegir l'usuari a la inscripció de l'activitat corresponent:
                    for (int k = 0; k < activity.getInscriptions().getNumElems(); k++){
                        if (activity.getInscriptions().getInscription(k).getNickName().equalsIgnoreCase(nickname)){
                            JOptionPane.showMessageDialog(null, "Ja estàs inscrit!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    if(activity.getNumElemsWaitingList() >= activity.getWaitingList().length && activity.getNumInscriptions() == activity.getInscriptions().getLenInscriptions()){
                        JOptionPane.showMessageDialog(null, "Ho sentim. Les inscripcions estan plenes.", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                    }
                    else if((activity.getNumInscriptions() == activity.getInscriptions().getLenInscriptions()) && activity.getNumElemsWaitingList() < activity.getWaitingList().length){ //Afegim a la waiting list
                        activity.addToWaitingList(newUser);
                    }
                    else if(activity.getNumInscriptions() < activity.getInscriptions().getLenInscriptions()){
                        activity.addInscriptions(newUser);
                    }
                }


            }


        }
        //-------------------


        //----- OPCIÓ 11 -----
        else if (option.equalsIgnoreCase("Usuaris inscrits en activitats i usuaris inscrits en llista d'espera")){
            if(users.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi han usuaris registrats!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            
            
            } else if (users.getNumElems() > 0){
                //Demanem el nom de l'activitat a mostrar per pantalla
                String activityName = JOptionPane.showInputDialog("Indica el nom de l'activitat");

                //Si l'usuari ha cancelat l'acció, la resposta serà null
                if(activityName != null){
                    if(activities.getActivity(activityName) != null){
                        Activities activity = activities.getActivity(activityName);
                        String phrase = "Usuaris inscrits a l'activitat: \n" +activity.getInscriptions();
                        phrase = phrase + "\nUsuaris a la llista d'espera de l'activitat: \n" +activity.getWaitingList();
                        JOptionPane.showMessageDialog(null, phrase, "Posició del usuaris a l'activitat", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "No existeix cap activitat amb aquest nom!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
        //-------------------


        //----- OPCIÓ 12 -----
        else if (option.equalsIgnoreCase("Eliminació d'usuari d'una activitat")){
            if(users.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi han usuaris registrats!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            
            } else if (users.getNumElems() > 0){
                if(activities.getNumElems() == 0){
                    JOptionPane.showMessageDialog(null, "No hi han activitats registrades!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                
                } else if (activities.getNumElems() > 0){
                    String activityName = JOptionPane.showInputDialog("Escriu el nom de l'activitat de la que vols eliminar l'usuari");
                    
                    //Si l'usuari ha cancelat l'acció, la resposta serà null
                    if(activityName != null){
                        int i = 0;
                        boolean found = false;
                        while (i < activities.getNumElems() && !found){
                            if(activities.getActivity(i).getActivityName().equalsIgnoreCase(activityName)){
                                found = true;
                            }
                            i++;
                        }
                        if(!found){
                            JOptionPane.showMessageDialog(null, "No existeix cap activitat amb aquest nom!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                        }

                        String userName = JOptionPane.showInputDialog("Escriu el nom de l'usuari: ");
                        int j = 0;
                        found = false;
                        while(j < users.getNumElems() && !found){
                            if(users.getUser(j).getNickname().equalsIgnoreCase(userName)){
                                found = true;
                            }
                            j++;
                        }
                        if(!found){
                            JOptionPane.showMessageDialog(null, "No hi ha cap usuari amb aquest nom!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                        }
                        i--;
                        j--;
                        activities.getActivity(i).removeInscription(users.getUser(j));
                        JOptionPane.showMessageDialog(null, "S'ha eliminat correctament l'usuari '"+users.getUser(j).getNickname()+"' de l'activitat" +activities.getActivity(i), "Eliminació d'usuari", JOptionPane.INFORMATION_MESSAGE);
                    }  
                }
            }
        }
        //-------------------


        //----- OPCIÓ 13 -----
        else if (option.equalsIgnoreCase("Afegir una nova activitat d'un dia")){
            String activityName = JOptionPane.showInputDialog(null, "Introdueix el nom de l'activitat: ", "Creació d'activitat", JOptionPane.INFORMATION_MESSAGE);
            
            String startInscriptionDateString = JOptionPane.showInputDialog(null, "Introdueix la data de començament de les inscripcions (aaaa mm dd): ", "Creació d'activitat", JOptionPane.INFORMATION_MESSAGE);
            LocalDate startInscription = LocalDate.parse(startInscriptionDateString);
            
            String finishInscriptionDateString = JOptionPane.showInputDialog(null, "Introdueix la data de finalització de les inscripcions (aaaa mm dd): ", "Creació d'activitat", JOptionPane.INFORMATION_MESSAGE);
            LocalDate finishInscription = LocalDate.parse(finishInscriptionDateString);

            String[] collectives = new String[3];
            int counter = 0;
            while(counter < 3){
                String collective = JOptionPane.showInputDialog(null, "Introdueix el col·lectiu" +counter+1+ " o -1 per acabar: ", "Afedir col·lectius", JOptionPane.QUESTION_MESSAGE);
                if(collective == null || collective.equals("-1")){
                    break;
                }
                //Si escriu una entrada vàlida
                if(!collective.trim().isEmpty()){
                    collectives[counter] = collective;
                    counter++;
                }
            }

            String limitPlacesString =JOptionPane.showInputDialog(null, "Introdueix el nombre límit de places per l'activitat: ", "Creació d'activitat", JOptionPane.INFORMATION_MESSAGE);
            int limitPlaces = Integer.parseInt(limitPlacesString);

            String city = JOptionPane.showInputDialog(null, "Introdueix la ciutat on es realitza l'activitat: ", "Creació d'activitat", JOptionPane.INFORMATION_MESSAGE);

            String activityDateString = JOptionPane.showInputDialog(null, "Introdueix el dia en el que es realitza l'activitat (aaaa mm dd): ", "Creació d'activitat", JOptionPane.INFORMATION_MESSAGE);
            LocalDate activityDay = LocalDate.parse(activityDateString);

            String startTimeString = JOptionPane.showInputDialog(null, "Introdueix l'horari en que comença l'activitat: (hora minuts)", "Creació d'activitat", JOptionPane.INFORMATION_MESSAGE);
            LocalTime startTime = LocalTime.parse(startTimeString);

            String finishTimeString = JOptionPane.showInputDialog(null, "Introdueix l'horari en que acaba l'activitat: (hora minuts)", "Creació d'activitat", JOptionPane.INFORMATION_MESSAGE);
            LocalTime finishTime = LocalTime.parse(finishTimeString);

            String priceString = JOptionPane.showInputDialog(null, "Introdueix el preu de l'activitat: (en decimals i amb coma)", "Creació d'activitat", JOptionPane.INFORMATION_MESSAGE);
            double price = Double.parseDouble(priceString);

            Activities oneDayActivity = new OneDayActivity("OneDay", activityName, startInscription, finishInscription, collectives, limitPlaces, city, activityDay, startTime, finishTime, price);
            activities.addActivity(oneDayActivity);
        }
        //-------------------


        //----- OPCIÓ 14 -----
        else if (option.equalsIgnoreCase("Afegir una nova activitat periòdica")){
            AppProgramaBenestar.option14(activities);
        }
        //-------------------


        //----- OPCIÓ 15 -----
        else if (option.equalsIgnoreCase("Afegir una nova activitat en línia")){
            String activityName = JOptionPane.showInputDialog(null, "Introdueix el nom de l'activitat", "Creació d'activitat", JOptionPane.INFORMATION_MESSAGE);

            String[] collectives = new String[3];
            int counter = 0;
            while(counter < 3){
                String collective = JOptionPane.showInputDialog(null, "Introdueix el col·lectiu" +counter+1+ " o -1 per acabar: ", "Afedir col·lectius", JOptionPane.QUESTION_MESSAGE);
                if(collective == null || collective.equals("-1")){
                    break;
                }
                //Si escriu una entrada vàlida
                if(!collective.trim().isEmpty()){
                    collectives[counter] = collective;
                    counter++;
                }
            }

            String startInscriptionDateString = JOptionPane.showInputDialog(null, "Introdueix la data de començament de les inscripcions (aaaa mm dd): ", "Creació d'activitat", JOptionPane.INFORMATION_MESSAGE);
            LocalDate startInscription = LocalDate.parse(startInscriptionDateString);
            
            String finishInscriptionDateString = JOptionPane.showInputDialog(null, "Introdueix la data de finalització de les inscripcions (aaaa mm dd): ", "Creació d'activitat", JOptionPane.INFORMATION_MESSAGE);
            LocalDate finishInscription = LocalDate.parse(finishInscriptionDateString);

            String startActivityDayString = JOptionPane.showInputDialog(null, "Introdueix la data d'inici de l'activitat (aaaa mm dd): ", "Creació d'activitat", JOptionPane.INFORMATION_MESSAGE);
            LocalDate startActivityDay = LocalDate.parse(startActivityDayString);
            
            String finishActivityDayString = JOptionPane.showInputDialog(null, "Introdueix la data de finalització de l'activitat (aaaa mm dd): ", "Creació d'activitat", JOptionPane.INFORMATION_MESSAGE);
            LocalDate finishActivityDay = LocalDate.parse(finishActivityDayString);

            String linkCourse = JOptionPane.showInputDialog(null, "Indica el link del curs: ", "Creació d'activitat", JOptionPane.INFORMATION_MESSAGE);

            Activities onlineActivity = new OnlineActivity("Online", activityName, collectives, startInscription, finishInscription, startActivityDay, finishActivityDay, linkCourse);
            activities.addActivity(onlineActivity);
        }
        //-------------------


        //----- OPCIÓ 16 ----
        else if (option.equalsIgnoreCase("Valoració d'una activitat")){
            AppProgramaBenestar.option16(users, activities);
        }
        //-------------------


        //----- OPCIÓ 17 -----
        else if (option.equalsIgnoreCase("Resum de valoracions de les activitats")){


            AppProgramaBenestar.option17(activities, AppProgramaBenestar.usedDate);
        }
        //-------------------


        //----- OPCIÓ 18 -----
        else if (option.equalsIgnoreCase("Resum de valoracions d'un usuari")){
            //Demanem el nom de l'usuari per veure les seves valoracions
            String userName = JOptionPane.showInputDialog("Indica l'usuari del qual vols veure les valoracions: ");

            //Si l'usuari ha cancelat l'acció, la resposta serà null
            if(userName != null){
                AppProgramaBenestar.option18(userName, users, activities);
            }
        }
        //-------------------


        //----- OPCIÓ 19 -----
        else if (option.equalsIgnoreCase("MItjanes de valoracions dels col·lectius")){
            AppProgramaBenestar.option19(activities);
        }
        //-------------------


        //----- OPCIÓ 20 -----
        else if (option.equalsIgnoreCase("Usuari més actiu d'un col·lectiu")){
            AppProgramaBenestar.option20(users);
        }
        //-------------------


        //----- OPCIÓ 21 -----
        else if (option.equalsIgnoreCase("Baixa d'activitats")){
            String phrase = "";
            if(activities.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi han activitats registrades!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            
            } else if(activities.getNumElems() > 0){
                phrase = "Llista d'activitats abans de la baixa: \n";
                for(int i=0; i<activities.getNumElems(); i++){
                    Activities activity = activities.getActivity(i);
                    phrase = phrase + "- "+activity.getActivityName();
                }

                for(int i=0; i<activities.getNumElems(); i++){
                    Activities activity = activities.getActivity(i); //Accés a cada activitat
                    InscriptionList maxPlaces = activity.getInscriptions(); //Màxim de places que té l'activitat
                    double places = (10.0/100)*(maxPlaces.getLenInscriptions());

                    int numPlaces = activity.getNumInscriptions(); //Número de places cobertes 

                    LocalDate finishDate = activity.getFinishDateInscriptions();
                    if(AppProgramaBenestar.usedDate.isAfter(finishDate)){
                        //Comprovem si és activitat online
                        if(activity instanceof OnlineActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                            if(numPlaces < 20){
                                String name = activity.getActivityName();
                                activities.removeActivity(name);
                            }
                        } else {
                            if(numPlaces < places){
                                String name = activity.getActivityName();
                                activities.removeActivity(name);
                            }
                        }
                    }
                }
                phrase = phrase + "\nLlista d'activitats actualitzada: \n";
                for(int i=0; i<activities.getNumElems(); i++){
                    Activities activity = activities.getActivity(i);
                    phrase = phrase + "- "+activity.getActivityName();
                }
            }
            JOptionPane.showMessageDialog(null, phrase, "Baixa d'activitats", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
