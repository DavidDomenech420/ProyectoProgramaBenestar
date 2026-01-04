package aplicació;

import java.awt.event.*;
import java.time.LocalDate;

import javax.swing.*;

import dades.Activities;
import dades.OneDayActivity;
import dades.OnlineActivity;
import dades.PeriodicActivity;
import list.ActivityList;
import list.UserList;

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
            JOptionPane.showMessageDialog(null, "Data actual al programa: " +AppProgramaBenestar.usedDate);
            String answer = JOptionPane.showInputDialog("Vol consultar una altra data? Si/no");

            if(answer != null && answer.equalsIgnoreCase("si")){
                String newDateString = JOptionPane.showInputDialog(null, "Introdueix una data (AAAA-MM-DD): ");
                //Comprovem que no hagi cancel·lat
                if(newDateString != null){
                    try{
                        AppProgramaBenestar.usedDate = LocalDate.parse(newDateString);
                        JOptionPane.showMessageDialog(null, "Data actualitzada: " +AppProgramaBenestar.usedDate);
                    } catch (Exception e){
                        JOptionPane.showMessageDialog(null, "Error en el format de la data");
                    }
                }
            }
        }
        //-------------------


        //----- OPCIÓ 2 -----
        else if (option.equalsIgnoreCase("Informació de les dades d'una llista")){
            //Demanem l'opció a mostrar per pantalla
            String answer = JOptionPane.showInputDialog("Escriu de què vols obtenir la informació (usuaris/activitats): ");

            //Si l'usuari ha cancelat l'acció, la resposta serà null
            if(answer != null){
                
                //SI L'OPCIÓ HA SIGIT USUARIS
                if(answer.equalsIgnoreCase("usuaris")){
                    try{
                        String answer2 = JOptionPane.showInputDialog("Escriu quin tipus d'usuari vols que es mosti:\n1: TOTS els usuaris\n2: professorat (PDI)\n3: personal tècnic i de gestió (PTGAS)\n4: estudiants\nEscriu la teva opció a continuació: ");
                        
                        //Si l'usuari ha cancelat l'acció, la resposta serà null
                        if(answer2 != null){
                            int userType = Integer.parseInt(answer2); //conversió a enter

                            //Opció 1 - Mostrar informació de tots els usuaris
                            if(userType == 1){
                                String phrase = "Has escollit que es mostri la informació de tots els usuaris: ";
                                for(int i=0; i<users.getNumElems(); i++){
                                    phrase = phrase + users.getUser(i).toString()+"\n";
                                }
                            }
                            //Opció 2 - Mostrar informació del professorat (PDI)
                            if(userType == 2){
                                String phrase = "Has escollit que es mostri la informació del professorat (PDI): ";
                                for(int i=0; i<users.getNumElems(); i++){
                                    if(users.getUser(i).getUserType().equalsIgnoreCase("PDI")){
                                        phrase = phrase + users.getUser(i).toString()+"\n";
                                    }
                                }
                            }
                            //Opció 3 - Mostrar informació del personal tècnic i de gestió (PTGAS)
                            if(userType == 3){
                                String phrase = "Has escollit que es mostri la informació del personal tècnic i de gestió (PTGAS): ";
                                for(int i=0; i<users.getNumElems(); i++){
                                    if(users.getUser(i).getUserType().equalsIgnoreCase("PTGAS")){
                                        phrase = phrase + users.getUser(i).toString()+"\n";
                                    }
                                }
                            }
                            //Opció 4 - Mostrar informació dels estudiants
                            if(userType == 4){
                                String phrase = "Has escollit que es mostri la informació dels estudiants: ";
                                for(int i=0; i<users.getNumElems(); i++){
                                    if(users.getUser(i).getUserType().equalsIgnoreCase("Student")){
                                        phrase = phrase + users.getUser(i).toString()+"\n";
                                    }
                                }
                            } 
                        }
                    } catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Error: Has d'introduir un número vàlid.");
                    }

                //Si L'OPCIÓ HA SIGUT ACTIVITATS
                } else if (answer.equalsIgnoreCase("activitats")){
                    try{
                        String answer3 = JOptionPane.showInputDialog("Escriu quin tipus d'activitat vols que es mosti:\n1: TOTES les activitats\n2: Activitats d'un dia\n3: Activitats periòdiques\n4: Activitats Online\nEscriu la teva opció a continuació: ");

                        //Si l'usuari ha cancelat l'acció, la resposta serà null
                        if(answer3 != null){
                            int activityType = Integer.parseInt(answer3); //conversió a enter

                            //Opció 1 - Mostrar la informació de totes les activitats
                            if(activityType == 1){
                                String phrase2 = "Has escollit que es mostri la informació de totes les activitats: ";
                                for(int i=0; i<activities.getNumElems(); i++){
                                    phrase2 = phrase2 + activities.getActivity(i);
                                }
                            }
                            //Opció 2 - Mostrar la informació de les activitats d'un dia
                            if(activityType == 2){
                                String phrase2 = "Has escollit que es mostri la informació de les activitats d'un dia: ";
                                for(int i=0; i<activities.getNumElems(); i++){
                                    if(activities.getActivity(i).getActivityType().equalsIgnoreCase("OneDay")){
                                        phrase2 = phrase2 + activities.getActivity(i);
                                    }
                                }
                            }
                            //Opció 3 - Mostrar la informació de les activitats periòdiques
                            if(activityType == 3){
                                String phrase2 = "Has escollit que es mostri la informació de les activitats periòdiques: ";
                                for(int i=0; i<activities.getNumElems(); i++){
                                    if(activities.getActivity(i).getActivityType().equalsIgnoreCase("Periodic")){
                                        phrase2 = phrase2 + activities.getActivity(i);
                                    }
                                }
                            }
                            //Opció 4 - Mostrar la informació de les activitats en línia
                            if(activityType == 4){
                                String phrase2 = "Has escollit que es mostri la informació de les activitats Online: ";
                                for(int i=0; i<activities.getNumElems(); i++){
                                    if(activities.getActivity(i).getActivityType().equalsIgnoreCase("Online")){
                                        phrase2 = phrase2 + activities.getActivity(i);
                                    }
                                }
                            }
                        }
                    } catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Error: Has d'introduir un número vàlid.");
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
                JOptionPane.showMessageDialog(null, "No hi ha activitats en període d'inscripció");

            } else if (openInscriptionActivities.getNumElems() > 0){
                String phrase = "Activitats en període d'inscripció: \n";

                for (int i = 0; i < openInscriptionActivities.getNumElems(); i++) {
                    int numPlazasDisp = openInscriptionActivities.getActivity(i).getInscriptions().getLenInscriptions() - openInscriptionActivities.getActivity(i).getNumInscriptions();
                    phrase = phrase + (i+1) + "- " + openInscriptionActivities.getActivity(i).getActivityName() + ", te " + numPlazasDisp + " places disponibles";
                }
                JOptionPane.showMessageDialog(null, phrase);
            }
        }
        //-------------------


        //----- OPCIÓ 4 -----
        else if (option.equalsIgnoreCase("Informació d'activitats amb classe en la data actual")){
            if(activities.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi ha activitats amb classe en la data actual");

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
                JOptionPane.showMessageDialog(null, phrase);
            }
        }
        //-------------------


        //----- OPCIÓ 5 -----
        else if (option.equalsIgnoreCase("Activitats actives en la data actual")){
            boolean thereAre = false;
            String message = "Activitats actives en la data actual ("+AppProgramaBenestar.usedDate+"): \n";

            for(int i=0; i<activities.getNumElems(); i++){
                Activities activity = activities.getActivity(i);

                //Comprovació per activitats d'un dia
                if(activity instanceof OneDayActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                    OneDayActivity oneDayAct = (OneDayActivity) activity;
                    if(oneDayAct.getDay().isEqual(AppProgramaBenestar.usedDate)){
                        thereAre = true;
                        message = message + oneDayAct.getActivityName()+ "(D'un dia)\n";
                    }
                //Comprovació per activitats online
                } else if(activity instanceof OnlineActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                    OnlineActivity onlineAct = (OnlineActivity) activity;
                    LocalDate start = onlineAct.getStartDateActivity();
                    LocalDate finish = onlineAct.getFinishDateActivity();
                    if(!AppProgramaBenestar.usedDate.isBefore(start) && !AppProgramaBenestar.usedDate.isAfter(finish)){ //Si posem 'usedDate.isAfter(start) && usedDate.isBefore(finish)', exclou els extrems
                        thereAre = true;
                        message = message + onlineAct.getActivityName();
                    }

                //Comprovació per activitats periòdiques
                } else if(activity instanceof PeriodicActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                    PeriodicActivity periodicAct = (PeriodicActivity) activity;
                    LocalDate start = periodicAct.getInicialDate();
                    int weeks = periodicAct.getWeeksOfActivity();
                    LocalDate finish = start.plusWeeks(weeks);
                    if(!AppProgramaBenestar.usedDate.isBefore(start) && !AppProgramaBenestar.usedDate.isAfter(finish)){
                        thereAre = true;
                        message = message + periodicAct.getActivityName();
                        
                    }
                }
            }
            if(thereAre == true){
                JOptionPane.showMessageDialog(null, message);
            } else {
                String messageNoActive = "No hi ha activitats actives en la data actual ("+AppProgramaBenestar.usedDate+")";
                JOptionPane.showMessageDialog(null, messageNoActive);
            }

        }
        //-------------------


        //----- OPCIÓ 6 -----
        else if (option.equalsIgnoreCase("Activitats amb places disponibles")){
            if(activities.getNumElems() == 0){
                JOptionPane.showMessageDialog(null, "No hi ha activitats amb places disponibles");

            } else if (activities.getNumElems() > 0){
                String phrase = "Activitats amb places disponibles: \n";

                for(int i = 0; i < activities.getNumElems(); i++){
                    if(activities.getActivity(i).getNumInscriptions() < activities.getActivity(i).getInscriptions().getLenInscriptions()){
                        int remaining = activities.getActivity(i).getInscriptions().getLenInscriptions() - activities.getActivity(i).getNumInscriptions();
                        phrase = phrase + activities.getActivity(i)+ " --> Places disponibles: "+remaining;
                    }
                }
                JOptionPane.showMessageDialog(null, phrase);
            }
        }
        //-------------------


        //----- OPCIÓ 7 -----
        else if (option.equalsIgnoreCase("Informació d'una activitat")){
            //Demanem el nom de l'activitat a mostrar per pantalla
            String activityName = JOptionPane.showInputDialog("Indica el nom de l'activitat");

            //Si l'usuari ha cancelat l'acció, la resposta serà null
            if(activityName != null){
                AppProgramaBenestar.option7(activities, activityName);
            }
        }
        //-------------------


        //----- OPCIÓ 8 -----
        else if (option.equalsIgnoreCase("Informació d'un usuari")){
            AppProgramaBenestar.option8(users);
        }
        //-------------------


        //----- OPCIÓ 9 -----
        else if (option.equalsIgnoreCase("Activitats on estàs inscrit")){
            AppProgramaBenestar.option9(activities, users);
        }
        //-------------------


        //----- OPCIÓ 10 -----
        else if (option.equalsIgnoreCase("Inscripció a una activitat")){
            AppProgramaBenestar.option10(activities, users);
        }
        //-------------------


        //----- OPCIÓ 11 -----
        else if (option.equalsIgnoreCase("Usuaris inscrits en activitats i usuaris inscrits en llista d'espera")){
            //Demanem el nom de l'activitat a mostrar per pantalla
            String activityName = JOptionPane.showInputDialog("Indica el nom de l'activitat");

            //Si l'usuari ha cancelat l'acció, la resposta serà null
            if(activityName != null){
                AppProgramaBenestar.option11(activities, activityName);
            }
        }
        //-------------------


        //----- OPCIÓ 12 -----
        else if (option.equalsIgnoreCase("Eliminació d'usuari d'una activitat")){
            AppProgramaBenestar.option12(activities, users);
        }
        //-------------------


        //----- OPCIÓ 13 -----
        else if (option.equalsIgnoreCase("Afegir una nova activitat d'un dia")){
            AppProgramaBenestar.option13(activities);
        }
        //-------------------


        //----- OPCIÓ 14 -----
        else if (option.equalsIgnoreCase("Afegir una nova activitat periòdica")){
            AppProgramaBenestar.option14(activities);
        }
        //-------------------


        //----- OPCIÓ 15 -----
        else if (option.equalsIgnoreCase("Afegir una nova activitat en línia")){
            AppProgramaBenestar.option15(activities);
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
