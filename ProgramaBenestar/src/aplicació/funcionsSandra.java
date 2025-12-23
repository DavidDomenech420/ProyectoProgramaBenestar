package aplicació;

import java.time.LocalDate;
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
            int any = keyboard.nextInt();
            int mes = keyboard.nextInt();
            int dia = keyboard.nextInt();
            usedDate = LocalDate.of(any, mes, dia);    //Actualització de la data
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
                LocalDate finish = start;
                for(int j=0; j<weeks; j++){
                    finish = finish.plusDays(7); //Afegirà 7 dies cada vegada que es repeteix el bucle (nombre de setmanes)
                }
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
    public static void option13(){
        //13. Afegir una nova activitat d'un dia.
        System.out.print("Introdueix el nom de l'activitat: "); //Demanem el nom de la nova activitat
        String activityName = keyboard.nextLine();

        //Col·lectius per afegir
        String collectives[] = new String[3];
        System.out.println("Introdueix quins col·lectius vols que participin (posa -1 per acabar): ");
        int contador = 0;
        String collective = "";
        while (contador < 3 && !collective.contains("-1")) {
            collective = keyboard.nextLine();
            if (!collective.contains("-1")) {
                collectives[contador] = collective;
                contador++;
            }
        }

        //Ciutat on es realitza l'activitat
        
    }
    //--------------------------------


    // ------ 17º OPCIÓ DEL MENU ------
    public static void option17(){
        //17. Mostrar resum de valoracions de les activitats: han d'estar acabades.
    }
    //--------------------------------


    // ------ 21º OPCIÓ DEL MENU ------
    public static void option21(){
        //21. Baixa d'activitats: donar de baixa les activitats que ja han acabat el període d'inscripció sense omplir el 10% de les places.
        // En activitats en línia es donarà si el número d'inscrits es inferior a 20 persones.
    }
    //--------------------------------
}
