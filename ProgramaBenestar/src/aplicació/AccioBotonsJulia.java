package aplicació;

import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.*;

import dades.Activities;
import dades.Inscriptions;
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
public class AccioBotonsJulia implements ActionListener {
    private ActivityList activities;
    private UserList users;

    //Si no es crea el constructor, la classe no té accés a les dades
    public AccioBotonsJulia (ActivityList activities, UserList users){
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
            String answer = JOptionPane.showInputDialog("Escriu de què vols obtenir la informació (usuaris/activitats): ");
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
                            String answer2 = JOptionPane.showInputDialog("Escriu quin tipus d'usuari vols que es mosti:\n1: TOTS els usuaris\n2: professorat (PDI)\n3: personal tècnic i de gestió (PTGAS)\n4: estudiants\nEscriu la teva opció a continuació: ");
                            
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
                            String answer3 = JOptionPane.showInputDialog("Escriu quin tipus d'activitat vols que es mosti:\n1: TOTES les activitats\n2: Activitats d'un dia\n3: Activitats periòdiques\n4: Activitats Online\nEscriu la teva opció a continuació: ");
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
                    phrase = phrase + (i+1) + "- " + openInscriptionActivities.getActivity(i).getActivityName() + ", te " + numPlazasDisp + " places disponibles";
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
                        JOptionPane.showMessageDialog(null, "S'ha eliminat correctament l'usuari '"+users.getUser(j).getNickname()+"' de l'activitat" +activities.getActivity(i), "Eliminaci");
                    }  
                }
            }
        }
        //-------------------


        //----- OPCIÓ 13 -----
        else if (option.equalsIgnoreCase("Afegir una nova activitat d'un dia")){
            String activityName = JOptionPane.showInputDialog("Introdueix el nom de l'activitat: ");
            String newDateString = JOptionPane.showInputDialog(null, "Introdueix una data (AAAA-MM-DD): ", "DATA DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
            
        }
        //-------------------


        //----- OPCIÓ 14 -----
        else if (option.equalsIgnoreCase("Afegir una nova activitat periòdica")){
            String activityName = JOptionPane.showInputDialog("Introdueix el nom de l'activitat: ");
            //------------------------------


            //----- Data on comencen les inscripcions -----
            String newDateString = JOptionPane.showInputDialog(null, "Introdueix la data de començament de les inscripcions (aaaa mm dd): ", "DATA", JOptionPane.INFORMATION_MESSAGE);
            LocalDate startInscription = LocalDate.parse(newDateString);
            //---------------------------------------------


            //----- Data on acaben les inscripcions -----
            newDateString = JOptionPane.showInputDialog(null, "Introdueix la data de finalització de les inscripcions (aaaa mm dd): ", "DATA", JOptionPane.INFORMATION_MESSAGE);
            LocalDate finishInscription = LocalDate.parse(newDateString);
            //-------------------------------------------


            //----- Col·lectius que poden participar en l'activitat -----
            String collectives[] = new String[3];
            String collective = "";
            collective = JOptionPane.showInputDialog("Introdueix quins col·lectius poden participar (posa -1 per acabar): ");
            int counter = 0;
            while (counter < 3 && !collective.contains("-1")) {
                collective = JOptionPane.showInputDialog("Següent: ");
                if (!collective.contains("-1")) {
                    collectives[counter] = collective;
                    counter++;
                }
            }
            //----------------------------------------------------------


            //----- Límit de places per l'activitat -----
            String slimitPlaces = JOptionPane.showInputDialog("Introdueix el nombre límit de places per l'activitat: ");
            int limitPlaces = Integer.parseInt(slimitPlaces);
            //-------------------------------------------


            //----- Ciutat on es realitza l'activitat -----
            String activityCity = JOptionPane.showInputDialog("Introdueix la ciutat on es realitza l'activitat: ");
            //---------------------------------------------


            //----- Dia en el que es realitza l'activitat -----
            String activityDay = JOptionPane.showInputDialog("Introdueix el dia en el que es realitza l'activitat (aaaa mm dd): ");
            //------------------------------------------------


            //----- Horari de començament de l'activitat -----
            String shour = JOptionPane.showInputDialog("Introdueix l'hora en que comença l'activitat (hora): ");
            int firstHour = Integer.parseInt(shour);
            String sminute = JOptionPane.showInputDialog("Introdueix el minut en que comença l'activitat (minut): ");
            int firstMinute = Integer.parseInt(sminute);
            LocalTime startTime = LocalTime.of(firstHour, firstMinute);
            //-----------------------------------------------


            //----- Horari de tancament de l'activitat -----
            shour = JOptionPane.showInputDialog("Introdueix l'hora en que cacaba l'activitat (hora): ");
            int finishHour = Integer.parseInt(shour);
            sminute = JOptionPane.showInputDialog("Introdueix el minut en que comença l'activitat (minut): ");
            int finishMinute = Integer.parseInt(sminute);
            LocalTime finishTime = LocalTime.of(finishHour, finishMinute);
            //---------------------------------------------


            //----- Preu de l'activitat -----
            String sactivityPrice = JOptionPane.showInputDialog("Introdueix el preu de l'activitat: (amb decimals, ex. 10,0)");
            double activityPrice = Double.parseDouble(sactivityPrice);
            //-------------------------------

            
            //----- Creació de la nova activitat amb els atribut demanats -----
            Activities oneDayActivity = new OneDayActivity("OneDay", activityName, startInscription, finishInscription, collectives, limitPlaces, activityCity, activityDay, startTime, finishTime, activityPrice);
            activities.addActivity(oneDayActivity);
            //-----------------------------------------------------------------
        }
        //-------------------


        //----- OPCIÓ 15 -----
        else if (option.equalsIgnoreCase("Afegir una nova activitat en línia")){
            AppProgramaBenestar.option15(activities);
        }
        //-------------------


        //----- OPCIÓ 16 ----
        else if (option.equalsIgnoreCase("Valoració d'una activitat")){

            activities = activities.activitiesFinished(AppProgramaBenestar.usedDate);
            String user = JOptionPane.showInputDialog("Escriu el teu usuari: ");
            int j = 0;
            boolean found = false;
            while (j < users.getNumElems() && !found){
                if (users.getUser(j).getNickname().equalsIgnoreCase(user)){
                    found = true;
                }
                j++;
            }
            if(!found){
                JOptionPane.showMessageDialog(null, "Aquest usuari no existeix", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            }

            String activity = JOptionPane.showInputDialog("Escriu el nom de l'activitat que vols crear: ");
            found = false;
            int i = 0;
            while (i < activities.getNumElems() && !found){
                if (activities.getActivity(i).getActivityName().equalsIgnoreCase(activity)){
                    found = true;
                }
                i++;
            }
            if (!found){
                JOptionPane.showMessageDialog(null, "Error, aquesta activitat no es pot evaluar.", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            }

            i--;
            j--;
            found = false;
            int k = 0;
            
            while (k < activities.getActivity(i).getInscriptions().getNumElems() && !found){
                if (activities.getActivity(i).getInscriptions().getInscription(k).getNickName().equalsIgnoreCase(users.getUser(j).getNickname())){
                    found = true;
                    try{
                        String sgrade = JOptionPane.showInputDialog("Escriu la nota que li vols donar a l'activittat: ");
                        int grade = Integer.parseInt(sgrade);
                        activities.getActivity(i).getInscriptions().getInscription(k).setAssessment(grade);
                    } catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Has d'introduir un número vàlid.", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                    }
                    
                }
                k++;
            }
            if (!found){
                JOptionPane.showMessageDialog(null, "No estàs inscrit en l'activitat.", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            }

        }
        //-------------------


        //----- OPCIÓ 17 -----
        else if (option.equalsIgnoreCase("Resum de valoracions de les activitats")){

            //Agafarem les activitats acabades, ja que aquestes seran les que es poden valorar
            ActivityList finishedActivities = activities.activitiesFinished(AppProgramaBenestar.usedDate);
            
            //Analitzem activitat per activitat per mostrar les valoracions de cadascuna
            String phrase = "";
            for (int i=0; i<finishedActivities.getNumElems(); i++){
                Activities activity = finishedActivities.getActivity(i);
                phrase = phrase + "\n" + activity.getActivityName() + ": "; //Nom de l'activitat a mostrar

                //Llista de les inscripcions d'aquesta activitat
                InscriptionList inscriptions = activity.getInscriptions();

                for(int j=0; j<inscriptions.getNumElems(); j++){
                    Inscriptions inscription = inscriptions.getInscription(j);
                    phrase = phrase + inscription.getAssessment() + " "; //Quantitat de la valoració
                }
            }
            JOptionPane.showMessageDialog(null, phrase, "Valoracions",  JOptionPane.INFORMATION_MESSAGE);
        }
        //-------------------


        //----- OPCIÓ 18 -----
        else if (option.equalsIgnoreCase("Resum de valoracions d'un usuari")){
            //Demanem el nom de l'usuari per veure les seves valoracions
            String user = JOptionPane.showInputDialog("Escriu el nom d'usuari del qual vols veure les valoracions: ");
            boolean found = false;
            int i = 0;
            while(!found && i < users.getNumElems()){
                if(users.getUser(i).getNickname().equalsIgnoreCase(user)){
                    found = true;
                }
                i++;
            }
            if(!found){
                JOptionPane.showMessageDialog(null, "Ho sentim. Aquest usuari no està registrat.", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                String phrase = "";
                for(i = 0; i < activities.getNumElems(); i++){
                    for(int j = 0; j < activities.getActivity(i).getNumInscriptions(); j++){
                        if(activities.getActivity(i).getInscriptions().getInscription(j).getNickName().equalsIgnoreCase(user)){
                            phrase = phrase + "Evaluació de l'activitat '" + activities.getActivity(i).getActivityName() + "' per part de " + activities.getActivity(i).getInscriptions().getInscription(j).getNickName() + ": ";
                            phrase = phrase + "Nota: " + activities.getActivity(i).getInscriptions().getInscription(j).getAssessment();
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, phrase, "Resum de valoracions",  JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //-------------------


        //----- OPCIÓ 19 -----
        else if (option.equalsIgnoreCase("MItjanes de valoracions dels col·lectius")){
            // Agafem Totes les activitats que ja s'han acabat
            ActivityList finishActivities = activities.activitiesFinished(AppProgramaBenestar.usedDate);
            // Recorrem totes les activitats i hem de mirar les inscripcions de cada activitat, despres recorrem les inscripcions i segons el colectiu sumem una variable de mitjana i altre i el contador
            for (int i = 0; i < finishActivities.getNumElems(); i++) {
                float mitjanaStudent = 0;
                float mitjanaPDI = 0;
                float mitjanaPTGAS = 0;
                int contadorStudent = 0;
                int contadorPDI = 0;
                int contadorPTGAS = 0;
                Activities activity = finishActivities.getActivity(i);
                InscriptionList inscriptions = activity.getInscriptions();
                for (int j = 0; j < inscriptions.getNumElems(); j++) {
                    Inscriptions inscription = inscriptions.getInscription(j);
                    // Pillem el colectiu
                    if (inscription.getUser().getUserType().equalsIgnoreCase("Student")) {
                        mitjanaStudent += inscription.getAssessment();
                        contadorStudent++;
                    }
                    else if (inscription.getUser().getUserType().equalsIgnoreCase("PDI")) {
                        mitjanaPDI += inscription.getAssessment();
                        contadorPDI++;
                    }
                    else if (inscription.getUser().getUserType().equalsIgnoreCase("PTGAS")) {
                        mitjanaPTGAS += inscription.getAssessment();
                        contadorPTGAS++;
                    }
                }

                // Fem les mitjanes i les mostrem
                String phrase = "Activitat '" + finishActivities.getActivity(i).getActivityName() + "' Valoracions - Student: " + (mitjanaStudent/contadorStudent) + ", PDI: " + (mitjanaPDI/contadorPDI) + ", PTGAS: " + (mitjanaPTGAS/contadorPTGAS);
                JOptionPane.showMessageDialog(null, phrase, "Mitjanes de valoracions dels col·lectius",  JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //-------------------


        //----- OPCIÓ 20 -----
        else if (option.equalsIgnoreCase("Usuari més actiu d'un col·lectiu")){
            String collective = JOptionPane.showInputDialog("Escriu el nom d'usuari del qual vols veure: ");
            if (!collective.equalsIgnoreCase("PDI") && !collective.equalsIgnoreCase("PTGAS") && !collective.equalsIgnoreCase("student")){
                JOptionPane.showMessageDialog(null, "Ho sentim. Aquest usuari no està registrat.", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            }
            User mostActive = null;
            for (int i = 0; i < users.getNumElems(); i++){
                if (users.getUser(i).getUserType().equalsIgnoreCase(collective)){
                    if (mostActive == null || mostActive.getNumInscriptions() < users.getUser(i).getNumInscriptions()){
                        mostActive = users.getUser(i);
                    }
                }
                
            }
            if (mostActive == null){
                JOptionPane.showMessageDialog(null, "Ho sentim. No hi ha cap usuari en aquest col·lectiu", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                String phrase = "Most active user: " + mostActive;
                JOptionPane.showMessageDialog(null, phrase, "Usuari més actiu",  JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //-------------------


        //----- OPCIÓ 21 -----
        else if (option.equalsIgnoreCase("Baixa d'activitats")){
            String phrase = "Llista d'activitats abans de la baixa: ";
            //Mostrem primer la llista sense actualitzar
            for(int i=0; i<activities.getNumElems(); i++){
                Activities activity = activities.getActivity(i);
                phrase = phrase + "- " +activity.getActivityName()+ "\n";
            }

            AppProgramaBenestar.option21(activities, AppProgramaBenestar.usedDate);

            //Mostrem la llista actualitzada
            phrase = phrase + "\nLlista d'activitats actualitzada: \n";
            for(int i=0; i<activities.getNumElems(); i++){
                Activities activity = activities.getActivity(i);
                phrase = phrase + "- " +activity.getActivityName()+ "\n";
            }

            //String final en una finestra
            JOptionPane.showMessageDialog(null, phrase);
        }
    }
}
