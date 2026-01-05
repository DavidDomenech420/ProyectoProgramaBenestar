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

        //---------------------------- OPCIÓ 1 ----------------------------
        //1. Mostrar informació sobre la data actual: pels jocs de proves farem diferents dates (per poder acceptar inscripcions o no).
        // Es mostrarà la data per poder modificar-la i fer o no operacions.
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
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 2 ----------------------------
        //2. Mostrar dades d'una llista: demanem de quina es vol mostrar (usuaris o activitats).
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
                                        phrase = "Has escollit que es mostri la informació de tots els usuaris: \n";
                                        for(int i=0; i<users.getNumElems(); i++){
                                            phrase = phrase + users.getUser(i).toString()+"\n";
                                        }
                                    }
                                    //Opció 2 - Mostrar informació del professorat (PDI)
                                    if(userType == 2){
                                        information = "TOTS ELS PROFESSORS";
                                        phrase = "Has escollit que es mostri la informació del professorat (PDI): \n";
                                        for(int i=0; i<users.getNumElems(); i++){
                                            if(users.getUser(i).getUserType().equalsIgnoreCase("PDI")){
                                                phrase = phrase + users.getUser(i).toString()+"\n";
                                            }
                                        }
                                    }
                                    //Opció 3 - Mostrar informació del personal tècnic i de gestió (PTGAS)
                                    if(userType == 3){
                                        information = "TOTS EL PERSONAL TÈCNIC I DE GESTIÓ";
                                        phrase = "Has escollit que es mostri la informació del personal tècnic i de gestió (PTGAS): \n";
                                        for(int i=0; i<users.getNumElems(); i++){
                                            if(users.getUser(i).getUserType().equalsIgnoreCase("PTGAS")){
                                                phrase = phrase + users.getUser(i).toString()+"\n";
                                            }
                                        }
                                    }
                                    //Opció 4 - Mostrar informació dels estudiants
                                    if(userType == 4){
                                        information = "TOTS ELS ESTUDIANTS";
                                        phrase = "Has escollit que es mostri la informació dels estudiants: \n";
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
                                    phrase2 = "Has escollit que es mostri la informació de totes les activitats: \n";
                                    for(int i=0; i<activities.getNumElems(); i++){
                                        phrase2 = phrase2 + activities.getActivity(i) + "\n";
                                    }
                                }
                                //Opció 2 - Mostrar la informació de les activitats d'un dia
                                if(activityType == 2){
                                    information = "ACTIVITATS D'UN DIA";
                                    phrase2 = "Has escollit que es mostri la informació de les activitats d'un dia: \n";
                                    for(int i=0; i<activities.getNumElems(); i++){
                                        if(activities.getActivity(i).getActivityType().equalsIgnoreCase("OneDay")){
                                            phrase2 = phrase2 + activities.getActivity(i) + "\n";
                                        }
                                    }
                                }
                                //Opció 3 - Mostrar la informació de les activitats periòdiques
                                if(activityType == 3){
                                    information = "ACTIVITATS PERIÒDIQUES";
                                    phrase2 = "Has escollit que es mostri la informació de les activitats periòdiques: \n";
                                    for(int i=0; i<activities.getNumElems(); i++){
                                        if(activities.getActivity(i).getActivityType().equalsIgnoreCase("Periodic")){
                                            phrase2 = phrase2 + activities.getActivity(i) + "\n";
                                        }
                                    }
                                }
                                //Opció 4 - Mostrar la informació de les activitats en línia
                                if(activityType == 4){
                                    information = "ACTIVITATS ONLINE";
                                    phrase2 = "Has escollit que es mostri la informació de les activitats Online: \n";
                                    for(int i=0; i<activities.getNumElems(); i++){
                                        if(activities.getActivity(i).getActivityType().equalsIgnoreCase("Online")){
                                            phrase2 = phrase2 + activities.getActivity(i) + "\n";
                                        }
                                    }
                                }
                                JOptionPane.showMessageDialog(null, phrase2, information, JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Error: Has d'introduir un número vàlid", "ERROR!", JOptionPane.WARNING_MESSAGE);
                        }
                    }                    
                }
            }
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 3 ----------------------------
        //3. Mostrar informació activitats en període d'inscripció: places disponibles o en llista d'espera.
        else if (option.equalsIgnoreCase("Informació de les activitats en període d'inscripció")){
            ActivityList openInscriptionActivities = activities.activitiesInscriptionOpen(AppProgramaBenestar.usedDate);

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
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 4 ----------------------------
        //4. Mostrar informació d'activitats en data actual: tota la informació (places, llista d'espera, etc).
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
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 5 ----------------------------
        //5. Mostrar activitats actives en la data actual: nom de les activitats; no cal classe, però la data actual entre la inicial i la final.
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
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 6 ----------------------------
        //6. Mostrar activitats amb places disponibles: nom d'aquestes. Tant si estan en termini d'inscripció o no.
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
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 7 ----------------------------
        //7. Mostrar informació d'una activitat: informació detallada a partir del nom d'aquesta.
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
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 8 ----------------------------
        //8. Mostrar informació d'usuari: informació detallada a partir del nom d'aquest.
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
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 9 ----------------------------
        //9. Mostrar activitats on estàs inscrit: totes a les que l'usuari s'ha apuntat.
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
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 10 ----------------------------
        //10. Inscripció a una activitat: disponible si es dona dins el termini i si aquesta es s'ofereix al col·lectiu que pertanyem.
        //L'usuari pot estar a la llista (usar alies) o no, en aquest cas s'haurà de demanar la resta d'informació.
        //Control de places disponibles o llista d'espera. Si la llista d'espera està plena, prohibit cap tipus d'inscripció.
        else if (option.equalsIgnoreCase("Inscripció a una activitat")){
            if(activities.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi han activitats registrades!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                ActivityList openInscriptionActivities = activities.activitiesInscriptionOpen(AppProgramaBenestar.usedDate);
                if(openInscriptionActivities.getNumElems() == 0){
                    JOptionPane.showMessageDialog(null, "No hi ha activitats amb data d'inscripció '"+AppProgramaBenestar.usedDate+"'", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                
                } else if(openInscriptionActivities.getNumElems() > 0){
                    String phrase = "Aquestes son les activitats disponibles actualment: \n";
                    for (int i = 0; i < openInscriptionActivities.getNumElems(); i++) {
                        int numPlazasDisp = openInscriptionActivities.getActivity(i).getInscriptions().getLenInscriptions() - openInscriptionActivities.getActivity(i).getNumInscriptions();
                        phrase = phrase + "- " + openInscriptionActivities.getActivity(i).getActivityName() + " --> Places disponibles: " +numPlazasDisp+ "\n";
                    }
                    JOptionPane.showMessageDialog(null, phrase, "Inscripció a una activitat",  JOptionPane.INFORMATION_MESSAGE);

                    String activityChosen = JOptionPane.showInputDialog(null, "Escriu l'activitat en la qual et vols inscriure: ", "Inscripció a una activitat",  JOptionPane.QUESTION_MESSAGE);
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
                        return;
                
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
                                    return;
                                }
                            }
                            i++;
                        }
                        if(!trobat){ // AFEGIR NOU USUARI
                            JOptionPane.showMessageDialog(null, "No estàs registrat com a usuari. A continuació se't faran una sèrie de preguntes per a registrar-te.", "Nou usuari",  JOptionPane.INFORMATION_MESSAGE);
                            String stringCollective = JOptionPane.showInputDialog(null, "A quin grup pertanys? \n[1] PDI\n[2] PTGAS\n[3] Estudiant\nEscriu el número a continuació: ", "Nou usuari",  JOptionPane.QUESTION_MESSAGE);
                            int numCollective = Integer.parseInt(stringCollective);
                            
                            // Col·lectiu:
                            while(numCollective != 1 && numCollective != 2 && numCollective != 3){
                                stringCollective = JOptionPane.showInputDialog(null, "No has introduit un número correcte. Torna a intentar-ho: ", "Nou usuari",  JOptionPane.QUESTION_MESSAGE);
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
                            boolean isPermited = false;
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
                            String email = JOptionPane.showInputDialog(null, "Quin és el teu e-mail (sense @)? Escriu-lo a continuació: ", "Nou usuari", JOptionPane.QUESTION_MESSAGE);

                            //Si és usuari PDI:
                            if(collective.equalsIgnoreCase("PDI")){
                                String campus = JOptionPane.showInputDialog(null, "En quin campus estàs?: ", "Nou usuari", JOptionPane.QUESTION_MESSAGE);
                                String department = JOptionPane.showInputDialog(null, "Quin és el teu departament?: ", "Nou usuari", JOptionPane.QUESTION_MESSAGE);
                                newUser = new PDIUser(collective, nickname, email, campus, department);
                                users.addUser(newUser);
                            }
                            else if(collective.equalsIgnoreCase("PTGAS")){
                                String campus = JOptionPane.showInputDialog(null, "En quin campus estàs?: ", "Nou usuari", JOptionPane.QUESTION_MESSAGE);
                                newUser = new PTGASUser(collective, nickname, email, campus);
                                users.addUser(newUser);
                            }
                            else{
                                String degree = JOptionPane.showInputDialog(null, "En quin grau estàs?: ", "Nou usuari", JOptionPane.QUESTION_MESSAGE);
                                String syear = JOptionPane.showInputDialog(null, "Quin va ser l'any en el qual vas començar el grau?: ", "Nou usuari", JOptionPane.QUESTION_MESSAGE);
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
                                i++;
                            }
                            if(!isPermited){
                                JOptionPane.showMessageDialog(null, "Ho sentim. El teu grup d'usuaris no pot realitzar aquesta activitat.", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                                return;
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
                            JOptionPane.showMessageDialog(null, "Com no hi han places disponibles, et trobes a la lista d'espera", "Inscripció a una activitat", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(activity.getNumInscriptions() < activity.getInscriptions().getLenInscriptions()){
                            activity.addInscriptions(newUser);
                            JOptionPane.showMessageDialog(null, "Has sigut inscrit correctament a l'activitat", "Inscripció a una activitat", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 11 ----------------------------
        //11. Mostrar els usuaris inscrits en activitats i els usuaris en llista d'espera: nom d'aquests.
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
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 12 ----------------------------
        //12. Eliminicació d'usuari d'una activitat:
        //Quan s'elimini un usuari que podia accedir a l'activitat, el primer de la llista d'espera passarà a ocupar el seu lloc.
        else if (option.equalsIgnoreCase("Eliminació d'usuari d'una activitat")){
            if(users.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi han usuaris registrats!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            
            } else if (users.getNumElems() > 0){
                if(activities.getNumElems() == 0){
                    JOptionPane.showMessageDialog(null, "No hi han activitats registrades!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                
                } else if (activities.getNumElems() > 0){
                    String activityName = JOptionPane.showInputDialog(null, "Escriu el nom de l'activitat de la que vols eliminar l'usuari", "Eliminació d'usuari", JOptionPane.QUESTION_MESSAGE);
                    
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

                        String userName = JOptionPane.showInputDialog(null, "Escriu el nom de l'usuari: ", "Eliminació d'usuari",  JOptionPane.QUESTION_MESSAGE);
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
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 13 ----------------------------
        //13. Afegir una nova activitat d'un dia.
        else if (option.equalsIgnoreCase("Afegir una nova activitat d'un dia")){
            String activityName = JOptionPane.showInputDialog(null, "Introdueix el nom de l'activitat: ", "Creació d'activitat d'un dia", JOptionPane.QUESTION_MESSAGE);
            
            if(activityName != null){
                String startInscriptionDateString = JOptionPane.showInputDialog(null, "Introdueix la data de començament de les inscripcions (AAAA-MM-DD): ", "Creació d'activitat d'un dia", JOptionPane.QUESTION_MESSAGE);
                
                if(startInscriptionDateString != null){
                    LocalDate startInscription = LocalDate.parse(startInscriptionDateString);
                    String finishInscriptionDateString = JOptionPane.showInputDialog(null, "Introdueix la data de finalització de les inscripcions (AAAA-MM-DD): ", "Creació d'activitat d'un dia", JOptionPane.QUESTION_MESSAGE);
                    
                    if(finishInscriptionDateString != null){
                        LocalDate finishInscription = LocalDate.parse(finishInscriptionDateString);

                        String[] collectives = new String[3];
                        int counter = 0;
                        int counter2 = 1; //Només per mostrar el missatge
                        while(counter < 3){
                            String collective = JOptionPane.showInputDialog(null, "Introdueix el col·lectiu " +counter2+ " o -1 per acabar: ", "Creació d'activitat d'un dia", JOptionPane.QUESTION_MESSAGE);
                            if(collective == null || collective.equals("-1")){
                                break;
                            }
                            //Si escriu una entrada vàlida
                            if(!collective.trim().isEmpty()){
                                collectives[counter] = collective;
                                counter++;
                                counter2++;
                            }
                        }
                        if(collectives != null){
                            String limitPlacesString =JOptionPane.showInputDialog(null, "Introdueix el nombre límit de places per l'activitat: ", "Creació d'activitat d'un dia", JOptionPane.QUESTION_MESSAGE);
                            
                            if(limitPlacesString != null){
                                int limitPlaces = Integer.parseInt(limitPlacesString);
                                String city = JOptionPane.showInputDialog(null, "Introdueix la ciutat on es realitza l'activitat: ", "Creació d'activitat d'un dia", JOptionPane.QUESTION_MESSAGE);
                                
                                if(city != null){
                                    String activityDateString = JOptionPane.showInputDialog(null, "Introdueix el dia en el que es realitza l'activitat (AAAA-MM-DD): ", "Creació d'activitat d'un dia", JOptionPane.QUESTION_MESSAGE);
                                    
                                    if(activityDateString != null){
                                        LocalDate activityDay = LocalDate.parse(activityDateString);
                                        int hourStart = Integer.parseInt(JOptionPane.showInputDialog(null, "Introdueix l'hora en que comença l'activitat: (no els minuts)", "Creació d'activitat d'un dia", JOptionPane.QUESTION_MESSAGE));
                                        int minutStart = Integer.parseInt(JOptionPane.showInputDialog(null, "Introdueix els minuts en que comença l'activitat: ", "Creació d'activitat d'un dia", JOptionPane.QUESTION_MESSAGE));
                                        LocalTime startTime = LocalTime.of(hourStart, minutStart);
                                        int hourFinish = Integer.parseInt(JOptionPane.showInputDialog(null, "Introdueix l'hora en que acaba l'activitat: (no els minuts)", "Creació d'activitat d'un dia", JOptionPane.QUESTION_MESSAGE));
                                        int minutFinish = Integer.parseInt(JOptionPane.showInputDialog(null, "Introdueix els minuts en que acaba l'activitat: ", "Creació d'activitat d'un dia", JOptionPane.QUESTION_MESSAGE));
                                        LocalTime finishTime = LocalTime.of(hourFinish, minutFinish);

                                        String priceString = JOptionPane.showInputDialog(null, "Introdueix el preu de l'activitat: (en decimals i amb punt)", "Creació d'activitat d'un dia", JOptionPane.QUESTION_MESSAGE);
                                        
                                        if(priceString != null){
                                            double price = Double.parseDouble(priceString);

                                            activities.addActivity(new OneDayActivity("OneDay", activityName, startInscription, finishInscription, collectives, limitPlaces, city, activityDay, startTime, finishTime, price));
                                            JOptionPane.showMessageDialog(null, "Activitat d'un dia, '"+activityName.toUpperCase()+"', afegida!");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 14 ----------------------------
        //14. Afegir una nova activitat periòdica.
        else if (option.equalsIgnoreCase("Afegir una nova activitat periòdica")){
            String activityName = JOptionPane.showInputDialog(null, "Introdueix el nom de l'activitat: ", "Creació d'activitat periòdica", JOptionPane.QUESTION_MESSAGE);
            
            if(activityName != null){
                String[] collectives = new String[3];
                int counter = 0;
                int counter2 = 1; //Només per mostrar el missatge
                while(counter < 3){
                    String collective = JOptionPane.showInputDialog(null, "Introdueix el col·lectiu " +counter2+ " o -1 per acabar: ", "Creació d'activitat periòdica", JOptionPane.QUESTION_MESSAGE);
                    if(collective == null || collective.equals("-1")){
                        break;
                    }
                    //Si escriu una entrada vàlida
                    if(!collective.trim().isEmpty()){
                        collectives[counter] = collective;
                        counter++;
                        counter2++;
                    }
                }

                if(collectives != null){
                    LocalDate startInscription = LocalDate.parse(JOptionPane.showInputDialog(null, "Introdueix la data de començament de les inscripcions (AAAA-MM-DD): ", "Creació d'activitat periòdica", JOptionPane.QUESTION_MESSAGE));
                    
                    if(startInscription != null){
                        LocalDate finishInscription = LocalDate.parse(JOptionPane.showInputDialog(null, "Introdueix la data de finalització de les inscripcions (AAAA-MM-DD): ", "Creació d'activitat periòdica", JOptionPane.QUESTION_MESSAGE));
                        
                        if(finishInscription != null){
                            int maxPlaces = Integer.parseInt(JOptionPane.showInputDialog(null, "Introdueix el nombre límit de places per l'activitat: ", "Creació d'activitat periòdica", JOptionPane.QUESTION_MESSAGE));
                            String dayOfWeek = JOptionPane.showInputDialog(null, "Introdueix el dia de la setmana en el que es realitza l'activitat (exemple: dilluns): ", "Creació d'activitat periòdica", JOptionPane.QUESTION_MESSAGE);

                            if(dayOfWeek != null){
                                int hourStart = Integer.parseInt(JOptionPane.showInputDialog(null, "Introdueix l'hora en que comença l'activitat: (no els minuts)", "Creació d'activitat periòdica", JOptionPane.QUESTION_MESSAGE));
                                int minutStart = Integer.parseInt(JOptionPane.showInputDialog(null, "Introdueix els minuts en que comença l'activitat: ", "Creació d'activitat periòdica", JOptionPane.QUESTION_MESSAGE));
                                LocalTime startTime = LocalTime.of(hourStart, minutStart);
                                int hourFinish = Integer.parseInt(JOptionPane.showInputDialog(null, "Introdueix l'hora en que acaba l'activitat: (no els minuts)", "Creació d'activitat periòdica", JOptionPane.QUESTION_MESSAGE));
                                int minutFinish = Integer.parseInt(JOptionPane.showInputDialog(null, "Introdueix els minuts en que acaba l'activitat: ", "Creació d'activitat periòdica", JOptionPane.QUESTION_MESSAGE));
                                LocalTime finishTime = LocalTime.of(hourFinish, minutFinish);
                                LocalDate activityOfActivity = LocalDate.parse(JOptionPane.showInputDialog(null, "Introdueix el dia en el que comença l'activitat (AAAA-MM-DD): ", "Creació d'activitat periòdica", JOptionPane.QUESTION_MESSAGE));

                                if(activityOfActivity != null){
                                    int weeksActivity = Integer.parseInt(JOptionPane.showInputDialog(null, "Introdueix el número de setmanes que perdurarà l'activitat: ", "Creació d'activitat periòdica", JOptionPane.QUESTION_MESSAGE));
                                    double price = Double.parseDouble(JOptionPane.showInputDialog(null, "Introdueix el preu de l'activitat: (en decimals i amb punt)", "Creació d'activitat periòdica", JOptionPane.QUESTION_MESSAGE));
                                    String centerName = JOptionPane.showInputDialog(null, "Introdueix el nom del centre on es realitza l'activitat: ", "Creació d'activitat periòdica", JOptionPane.QUESTION_MESSAGE);

                                    if(centerName != null){
                                        String city = JOptionPane.showInputDialog(null, "Introdueix el nom de la ciutat on es realitza l'activitat: ", "Creació d'activitat periòdica", JOptionPane.QUESTION_MESSAGE);

                                        if(city != null){
                                            activities.addActivity(new PeriodicActivity("Periodic", activityName, startInscription, finishInscription, collectives, maxPlaces, dayOfWeek, startTime, finishTime, activityOfActivity, weeksActivity, price, centerName, city));
                                            JOptionPane.showMessageDialog(null, "Activitat periòdica, '"+activityName.toUpperCase()+"', afegida!");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 15 ----------------------------
        //15. Afegir una nova activitat online.
        // Demanem al usuari que ens digui la informació que vol afegir a l'activitat
        else if (option.equalsIgnoreCase("Afegir una nova activitat en línia")){
            String activityName = JOptionPane.showInputDialog(null, "Introdueix el nom de l'activitat", "Creació d'activitat online", JOptionPane.QUESTION_MESSAGE);

            if(activityName != null){
                String[] collectives = new String[3];
                int counter = 0;
                int counter2 = 1; //Només per mostrar el missatge
                while(counter < 3){
                    String collective = JOptionPane.showInputDialog(null, "Introdueix el col·lectiu " +counter2+ " o -1 per acabar: ", "Creació d'activitat online", JOptionPane.QUESTION_MESSAGE);
                    if(collective == null || collective.equals("-1")){
                        break;
                    }
                    //Si escriu una entrada vàlida
                    if(!collective.trim().isEmpty()){
                        collectives[counter] = collective;
                        counter++;
                        counter2++;
                    }
                }

                if(collectives != null){   
                    String startInscriptionDateString = JOptionPane.showInputDialog(null, "Introdueix la data de començament de les inscripcions (AAAA-MM-DD): ", "Creació d'activitat online", JOptionPane.QUESTION_MESSAGE);
                    
                    if(startInscriptionDateString != null){
                        LocalDate startInscription = LocalDate.parse(startInscriptionDateString);
                        String finishInscriptionDateString = JOptionPane.showInputDialog(null, "Introdueix la data de finalització de les inscripcions (AAAA-MM-DD): ", "Creació d'activitat online", JOptionPane.QUESTION_MESSAGE);
                        
                        if(finishInscriptionDateString != null){
                            LocalDate finishInscription = LocalDate.parse(finishInscriptionDateString);
                            String startActivityDayString = JOptionPane.showInputDialog(null, "Introdueix la data d'inici de l'activitat (AAAA-MM-DD): ", "Creació d'activitat online", JOptionPane.QUESTION_MESSAGE);
                            
                            if(startActivityDayString != null){
                                LocalDate startActivityDay = LocalDate.parse(startActivityDayString);
                                String finishActivityDayString = JOptionPane.showInputDialog(null, "Introdueix la data de finalització de l'activitat (AAAA-MM-DD): ", "Creació d'activitat online", JOptionPane.QUESTION_MESSAGE);
                                
                                if(finishActivityDayString != null){
                                    LocalDate finishActivityDay = LocalDate.parse(finishActivityDayString);
                                    String linkCourse = JOptionPane.showInputDialog(null, "Indica el link del curs: ", "Creació d'activitat", JOptionPane.QUESTION_MESSAGE);

                                    if(linkCourse != null){
                                        activities.addActivity(new OnlineActivity("Online", activityName, collectives, startInscription, finishInscription, startActivityDay, finishActivityDay, linkCourse));
                                        JOptionPane.showMessageDialog(null, "Activitat en línia, '"+activityName.toUpperCase()+"', afegida!");
                                    }
                                }
                            }
                        }
                    }
                }
            }
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 16 ----------------------------
        //16. Valoració d'una activitat
        //Requisits per a que l'usuari la pugui valorar: 
            //1- l'activitat ha d'haver acabat.
            //2- l'usuari ha d'haver assistit a l'activitat.
        else if (option.equalsIgnoreCase("Valoració d'una activitat")){
            activities = activities.activitiesFinished(AppProgramaBenestar.usedDate);

            if(users.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi ha cap usuari registrat!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            
            } else if (users.getNumElems() > 0){
                String user = JOptionPane.showInputDialog("Escriu el teu nom d'usuari: ");
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

                if(activities.getNumElems() == 0){
                    JOptionPane.showMessageDialog(null, "No hi ha cap usuari registrat!", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                
                } else if (activities.getNumElems() > 0){
                    String activity = JOptionPane.showInputDialog(null, "Escriu el nom de l'activitat que vols valorar: ", "Valoració d'una activitat", JOptionPane.QUESTION_MESSAGE);
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
                                String sgrade = JOptionPane.showInputDialog(null, "Escriu la nota que li vols donar a l'activitat: ", "Valoració d'una activitat", JOptionPane.QUESTION_MESSAGE);
                                int grade = Integer.parseInt(sgrade);
                                activities.getActivity(i).getInscriptions().getInscription(k).setAssessment(grade);
                                JOptionPane.showMessageDialog(null, "La teva valoració s'ha guardat correctament: "+grade, "Valoració d'una activitat", JOptionPane.INFORMATION_MESSAGE);

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
            }
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 17 ----------------------------
        //17. Mostrar resum de valoracions de les activitats: han d'estar acabades.
        else if (option.equalsIgnoreCase("Resum de valoracions de les activitats")){
            
            //Agafarem les activitats acabades, ja que aquestes seran les que es poden valorar
            ActivityList finishedActivities = activities.activitiesFinished(AppProgramaBenestar.usedDate);
            
            //Analitzem activitat per activitat per mostrar les valoracions de cadascuna
            if(finishedActivities.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "Error! No hi ha activitats acabades", "ERROR!", JOptionPane.WARNING_MESSAGE);
            
            } else if (finishedActivities.getNumElems() > 0){
                String phrase = "";
                for (int i=0; i<finishedActivities.getNumElems(); i++){
                    Activities activity = finishedActivities.getActivity(i);
                    phrase = phrase + activity.getActivityName(); //Nom de l'activitat a mostrar

                    //Llista de les inscripcions d'aquesta activitat
                    InscriptionList inscriptions = activity.getInscriptions();

                    for(int j=0; j<inscriptions.getNumElems(); j++){
                        Inscriptions inscription = inscriptions.getInscription(j);
                        phrase = phrase + inscription.getAssessment() + " "; //Quantitat de la valoració
                    }
                }
                JOptionPane.showMessageDialog(null, phrase, "Resum de les valoracions",  JOptionPane.INFORMATION_MESSAGE);
            }
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 18 ----------------------------
        //18. Mostrar resum de valoracions d'un usuari: total de valoracions fetes per l'usuari indicat.
        else if (option.equalsIgnoreCase("Resum de valoracions d'un usuari")){
            if(users.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "Error! No hi ha usuaris registrats", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            
            } else if (users.getNumElems() > 0){
                //Demanem el nom de l'usuari per veure les seves valoracions
                String user = JOptionPane.showInputDialog(null, "Escriu el nom d'usuari del qual vols veure les valoracions: ", "Valoracions d'un usuari", JOptionPane.QUESTION_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, phrase, "Valoracions d'un usuari", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 19 ----------------------------
        //19. Mostrar mitjanes de valoracions dels col·lectius:
        // Objectiu -> comparar si els usuaris dels diferents col·lectius valoren igual o no.
        else if (option.equalsIgnoreCase("MItjanes de valoracions dels col·lectius")){
            if(users.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "Error! No hi ha usuaris registrats", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            
            } else if (users.getNumElems() > 0){
                if(activities.getNumElems() == 0){
                    JOptionPane.showMessageDialog(null, "Error! No hi ha activitats registrades", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
                
                } else if (activities.getNumElems() > 0){
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
            }
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 20 ----------------------------
        //20. Mostrar l'usuari més actiu del col·lectiu indicat: l'usuari més actiu serà el que s'ha apuntat a més activitats.
        // En cas d'empat entre usuaris, s'escollirà a qualsevol usuari que compleixi els requisits.
        else if (option.equalsIgnoreCase("Usuari més actiu d'un col·lectiu")){
            if(users.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "Error! No hi ha usuaris registrats", "ATENCIÓ!", JOptionPane.WARNING_MESSAGE);
            
            } else if (users.getNumElems() > 0){
                String collective = JOptionPane.showInputDialog(null, "Escriu el col·lectiu que vols comprovar (PDI, PTGAS, Student): ", "Usuari més actiu d'un col·lectiu", JOptionPane.QUESTION_MESSAGE);
                if (!collective.equalsIgnoreCase("PDI") && !collective.equalsIgnoreCase("PTGAS") && !collective.equalsIgnoreCase("Student")){
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
                    String phrase = "Usuari més actiu del col·lectiu '" +collective+ "' : \n" + mostActive;
                    JOptionPane.showMessageDialog(null, phrase, "Usuari més actiu",  JOptionPane.INFORMATION_MESSAGE);
                }
            }
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------


        //---------------------------- OPCIÓ 21 ----------------------------
        //21. Baixa d'activitats: donar de baixa les activitats que ja han acabat el període d'inscripció sense omplir el 10% de les places.
        // En activitats en línia es donarà si el número d'inscrits es inferior a 20 persones.
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
                JOptionPane.showMessageDialog(null, phrase, "Baixa d'activitats", JOptionPane.INFORMATION_MESSAGE);
            }
            AppProgramaBenestar.guardarDades(activities, users);
        }
        //-----------------------------------------------------------------

        //---------------------------- OPCIÓ 21 ----------------------------
        //22. Guardar i sortir
        else if (option.equalsIgnoreCase("Guardar i Sortir")){
            AppProgramaBenestar.guardarDades(activities, users);
            JOptionPane.showMessageDialog(null, "Dades guardades correctament!", "GUARDAT!", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        //-----------------------------------------------------------------
    }
}