package aplicació;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import dades.Activities;
import Usuaris.User;

import list.ActivityList;
import list.UserList;

import dades.OneDayActivity;
import dades.OnlineActivity;
import dades.PeriodicActivity;

public class funcionsSandra {
    static Scanner keyboard = new Scanner(System.in);
    private static LocalDate usedDate = LocalDate.now();

    // ------ 1º OPCIÓ DEL MENU ------
    public static void option1(){
        //1. Mostrar informació sobre la data actual: pels jocs de proves farem diferents dates (per poder acceptar inscripcions o no).
        // Es mostrarà la data per poder modificar-la i fer o no operacions.
        System.out.println("Data actual al programa: " +usedDate);
        System.out.println("Vol consultar una altra data? Si/no");
        String answer = keyboard.nextLine();
        if(answer.equalsIgnoreCase("si")){
            System.out.println("Introdueix una data (aaaa-mm-dd): ");
            int year = keyboard.nextInt();
            int month = keyboard.nextInt();
            int day = keyboard.nextInt();
            usedDate = LocalDate.of(year, month, day);    //Actualització de la data
            System.out.println("Data actualitzada: " +usedDate);
        }
    }
    //--------------------------------


    // ------ 5º OPCIÓ DEL MENU ------
    public static void option5(ActivityList activities, LocalDate usedDate){
        //5. Mostrar activitats actives en la data actual: nom de les activitats; no cal classe, però la data actual entre la inicial i la final.
        System.out.println("Activitats actives en la data actual ("+usedDate+")");
        for(int i=0; i<activities.getNumElems(); i++){
            Activities activity = activities.getActivity(i);

            //Comprovació per activitats d'un dia
            if(activity instanceof OneDayActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                OneDayActivity oneDayAct = (OneDayActivity) activity;
                if(oneDayAct.getDay().isEqual(usedDate)){
                    System.out.println("Activitat d'un dia: " +oneDayAct.getActivityName()); //Mostrem el nom
                }

            //Comprovació per activitats online
            }else if(activity instanceof OnlineActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                OnlineActivity onlineAct = (OnlineActivity) activity;
                LocalDate start = onlineAct.getStartDateActivity();
                LocalDate finish = onlineAct.getFinishDateActivity();
                if(!usedDate.isBefore(start) && !usedDate.isAfter(finish)){ //Si posem 'usedDate.isAfter(start) && usedDate.isBefore(finish)', exclou els extrems
                    System.out.println("Activitat online: " +onlineAct.getActivityName()); //Mostrem el nom
                }

            //Comprovació per activitats periòdiques
            }else if(activity instanceof PeriodicActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                PeriodicActivity periodicAct = (PeriodicActivity) activity;
                LocalDate start = periodicAct.getInicialDate();
                int weeks = periodicAct.getWeeksOfActivity();
                LocalDate finish = start.plusWeeks(weeks);
                if(!usedDate.isBefore(start) && !usedDate.isAfter(finish)){
                    System.out.println("Activitat periòdica: " +periodicAct.getActivityName()); //Mostrem el nom
                }
            }
        }
    }   
    //--------------------------------


    // ------ 9º OPCIÓ DEL MENU ------
    public static void option9(ActivityList activities, UserList users){
        //9. Mostrar activitats on estàs inscrit: totes a les que l'usuari s'ha apuntat.
        System.out.println("Introdueix el teu nom d'usuari: ");
        String nomUsuari = keyboard.nextLine();
        for(int i=0; i<activities.getNumElems(); i++){
            Activities activity = activities.getActivity(i);
            User[] inscriptedUsers = activity.getInscriptions();

            System.out.println("Activitats on estàs inscrit: ");
            for(int j=0; j<inscriptedUsers.length; j++){
                if((inscriptedUsers[j].getNickname()).equalsIgnoreCase(nomUsuari)){
                    System.out.println(activity.getActivityName());
                }
            }
        }
    }
    //--------------------------------


    // ------ 13º OPCIÓ DEL MENU ------
    public static void option13(ActivityList activities){
        //13. Afegir una nova activitat d'un dia.

        //----- Nom de l'activitat -----
        System.out.print("Introdueix el nom de l'activitat: "); //Demanem el nom de la nova activitat
        String activityName = keyboard.nextLine();
        //------------------------------


        //----- Data on comencen les inscripcions -----
        System.out.println("Introdueix la data de començament de les inscripcions (aaaa-mm-dd): ");
        int inscriptionYear = keyboard.nextInt();
        int inscriptionMonth = keyboard.nextInt();
        int inscriptionDay = keyboard.nextInt();
        LocalDate startInscription = LocalDate.of(inscriptionYear, inscriptionMonth, inscriptionDay);
        //---------------------------------------------


        //----- Data on acaben les inscripcions -----
        System.out.println("Introdueix la data de finalització de les inscripcions (aaaa-mm-dd): ");
        int insFinishYear = keyboard.nextInt();
        int insFinishMonth = keyboard.nextInt();
        int insFinishDay = keyboard.nextInt();
        LocalDate finishInscription = LocalDate.of(insFinishYear, insFinishMonth, insFinishDay);
        //-------------------------------------------


        //----- Col·lectius que poden participar en l'activitat -----
        String collectives[] = new String[3];
        System.out.println("Introdueix quins col·lectius poden participar (posa -1 per acabar): ");
        int counter = 0;
        String collective = "";
        while (counter < 3 && !collective.contains("-1")) {
            collective = keyboard.nextLine();
            if (!collective.contains("-1")) {
                collectives[counter] = collective;
                counter++;
            }
        }
        //----------------------------------------------------------


        //----- Límit de places per l'activitat -----
        System.out.println("Introdueix el nombre límit de places per l'activitat: ");
        int limitPlaces = keyboard.nextInt();
        //-------------------------------------------


        //----- Ciutat on es realitza l'activitat -----
        System.out.println("Introdueix la ciutat on es realitza l'activitat: ");
        String activityCity = keyboard.nextLine();
        //---------------------------------------------


        //----- Dia en el que es realitza l'activitat -----
        System.out.println("Introdueix el dia en el que es realitza l'activitat (aaaa-mm-dd): ");
        int ActivityYear = keyboard.nextInt();
        int ActivityMonth = keyboard.nextInt();
        int ActivityDay = keyboard.nextInt();
        LocalDate activityDay = LocalDate.of(ActivityYear, ActivityMonth, ActivityDay);
        //------------------------------------------------


        //----- Horari de començament de l'activitat -----
        System.out.println("Introdueix l'horari en que comença l'activitat: ");
        int firstHour = keyboard.nextInt();
        int firstMinute = keyboard.nextInt();
        LocalTime startTime = LocalTime.of(firstHour, firstMinute);
        //-----------------------------------------------


        //----- Horari de tancament de l'activitat -----
        System.out.println("Introdueix l'horari en que acaba l'activitat: ");
        int finalHour = keyboard.nextInt();
        int finalMinute = keyboard.nextInt();
        LocalTime finishTime = LocalTime.of(finalHour, finalMinute);
        //---------------------------------------------


        //----- Preu de l'activitat -----
        System.out.println("Introdueix el preu de l'activitat: ");
        double activityPrice = keyboard.nextDouble();
        //-------------------------------

        
        //----- Creació de la nova activitat amb els atribut demanats -----
        Activities oneDayActivity = new OneDayActivity("One Day", activityName, startInscription, finishInscription, collectives, limitPlaces, activityCity, activityDay, startTime, finishTime, activityPrice);
        activities.addActivity(oneDayActivity);
        //-----------------------------------------------------------------
        
        
    }
    //--------------------------------


    // ------ 17º OPCIÓ DEL MENU ------
    public static void option17(ActivityList activities, LocalDate usedDate){
        //17. Mostrar resum de valoracions de les activitats: han d'estar acabades.
        for(int i=0; i<activities.getNumElems(); i++){
            Activities activity = activities.getActivity(i);

            //Comprovem si és activitat d'un dia
            if(activity instanceof OneDayActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                OneDayActivity oneDayAct = (OneDayActivity) activity;
                if((oneDayAct.getDay()).isAfter(usedDate)){ //Si la data ja ha passat, l'activitat es pot valorar
                    //VALORACIÓ
                }

            //Comprovem si és activitat online
            }else if(activity instanceof OnlineActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                OnlineActivity onlineAct = (OnlineActivity) activity;
                LocalDate finish = onlineAct.getFinishDateActivity();
                if(usedDate.isAfter(finish)){  //Si la data és posterior a  la final, vol dir que l'activitat ha acabat, llavors es pot valorar
                    //VALORACIÓ
                }

            //Comprovem si és activitat periòdica
            }else if(activity instanceof PeriodicActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                PeriodicActivity periodicAct = (PeriodicActivity) activity;
                LocalDate start = periodicAct.getInicialDate();
                int weeks = periodicAct.getWeeksOfActivity();
                LocalDate finish = start.plusWeeks(weeks);
                if(usedDate.isAfter(finish)){
                    //VALORACIÓ
                }
            }
        }
    }
    //--------------------------------


    // ------ 21º OPCIÓ DEL MENU ------
    public static void option21(ActivityList activities, LocalDate usedDate){
        //21. Baixa d'activitats: donar de baixa les activitats que ja han acabat el període d'inscripció sense omplir el 10% de les places.
        // En activitats en línia es donarà si el número d'inscrits es inferior a 20 persones.

        for(int i=0; i<activities.getNumElems(); i++){
            Activities activity = activities.getActivity(i); //Accés a cada activitat

            //Comprovem si és activitat online
            if(activity instanceof OnlineActivity){ //Comprovació que el tipus dinàmic sigui la classe filla
                OnlineActivity onlineActivity = (OnlineActivity) activity;
                LocalDate finishDate = onlineActivity.getFinishDateInscriptions();
                if(usedDate.isAfter(finishDate)){
                    User[] maxPlaces = onlineActivity.getInscriptions();
                    if(){
                        //Inscriptions de user son el límite máximo de plazas
                        //NumInscriptions es el número actual de plazas
                    }
                }
            } else {
                
            }
        }
    }
    //--------------------------------
}
