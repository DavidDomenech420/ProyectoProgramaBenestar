package aplicació;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import dades.Activities;
import dades.OneDayActivity;
import dades.OnlineActivity;
import dades.PeriodicActivity;
import list.ActivityList;
import list.UserList;
import Usuaris.PDIUser;
import Usuaris.PTGASUser;
import Usuaris.StudentUser;
import Usuaris.User;

public class funcionsBiel {

    private static LocalDate usedDate = LocalDate.now();

    static Scanner keyboard = new Scanner(System.in);

    public static void option4(ActivityList activities){
        //4. Mostrar informació d'activitats en data actual: tota la informació (places, llista d'espera, etc).
        for (int i = 0; i < activities.getNumElems(); i++){
            if (activities.getActivity(i) instanceof OneDayActivity){
                OneDayActivity OneDay = (OneDayActivity) activities.getActivity(i);
                if (OneDay.getDay().isEqual(usedDate)){
                    System.out.println(OneDay);
                }
            }
            else if (activities.getActivity(i) instanceof OnlineActivity){
                OnlineActivity Online = (OnlineActivity) activities.getActivity(i);
                if (Online.getStartDateActivity().isBefore(usedDate) && Online.getFinishDateActivity().isAfter(usedDate)){
                    System.out.println(Online);
                }
            }
            else if(activities.getActivity(i) instanceof PeriodicActivity){
                PeriodicActivity Periodic = (PeriodicActivity) activities.getActivity(i);
                if (Periodic.getInicialDate().isBefore(usedDate) && Periodic.getInicialDate().plusDays((long) (Periodic.getWeeksOfActivity()*7)).isAfter(usedDate)){
                    System.out.println(Periodic);
                }
            }
        }
    }

    // ------ 8º OPCIÓ DEL MENU ------
    public static void option8(UserList List){
        //8. Mostrar informació d'usuari: informació detallada a partir del nom d'aquest.
        String Name = keyboard.nextLine();
        User aux = List.getUser(Name);
        System.out.println(aux);
    }
    //--------------------------------

    // ------ 12º OPCIÓ DEL MENU ------
    public static void option12(ActivityList listAct, UserList listUser){
        //12. Eliminicació d'usuari d'una activitat:
        //Quan s'elimini un usuari que podia accedir a l'activitat, el primer de la llista d'espera passarà a ocupar el seu lloc.
        System.out.println("Escriu l'activitat de la que vols eliminar l'usuari");
        String activity = keyboard.nextLine();
        boolean found = false;
        int i = 0;
        while (i < listAct.getNumElems() && !found){
            if (listAct.getActivity(i).getActivityName().equalsIgnoreCase(activity)){
                found = true;
            }
            i++;
        }
        if (!found){
            System.out.println("Error, no hi ha una activitat amb aquest nom");
            return;
        }
        System.out.println("Escriu el nom de l'usuari");
        String user = keyboard.nextLine();
        int j = 0;
        found = false;
        while (j < listUser.getNumElems() && !found){
            if (listUser.getUser(j).getNickname().equalsIgnoreCase(user)){
                found = true;
            }
            j++;
        }
        if (!found){
            System.out.println("Error, no hi ha un usuari amb aquest nom");
            return;
        }
        i--;
        j--;
        listAct.getActivity(i).removeInscription(listUser.getUser(j));
    }
    //--------------------------------

    // ------ 16º OPCIÓ DEL MENU ------
    public static void option16(UserList listUser, ActivityList listAct){
        //16. Valoració d'una activitat: 
        //requisits per a que l'usuari la pugui valorar: 
            //1- l'activitat ha d'haver acabat.
            //2- l'usuari ha d'haver assistit a l'activitat.
        System.out.println("Write your username: ");
        String user = keyboard.nextLine();
        int j = 0;
        boolean found = false;
        while (j < listUser.getNumElems() && !found){
            if (listUser.getUser(j).getNickname().equalsIgnoreCase(user)){
                found = true;
            }
            j++;
        }
        if (!found){
            System.out.println("Error, this user does not exist");
            return;
        }

        System.out.println("Write the name of the activity you want to assess");
        String activity = keyboard.nextLine();
        found = false;
        int i = 0;
        while (i < listAct.getNumElems() && !found){
            if (listAct.getActivity(i).getActivityName().equalsIgnoreCase(activity)){
                found = true;
            }
            i++;
        }
        if (!found){
            System.out.println("Error, this activity does not exist");
            return;
        }

        i--;
        j--;
        found = false;
        int k = 0;
        while (k < listAct.getActivity(i).getInscriptions().getNumElems() && !found){
            if (listAct.getActivity(i).getInscriptions().getInscription(k).getNickName().equalsIgnoreCase(listUser.getUser(j).getNickname())){
                found = true;
                try{
                    System.out.println("Enter the grade you want to give to the activity: ");
                    int grade = Integer.parseInt(keyboard.nextLine());
                    listAct.getActivity(i).getInscriptions().getInscription(k).setAssessment(grade);
                } catch(NumberFormatException e){
                    System.out.println("Error, you have to enter a valid number");
                }
                
            }
            k++;
        }

    }
    //--------------------------------


    // ------ 20º OPCIÓ DEL MENU ------
    public static void option20(UserList listUser){
        //20. Mostrar l'usuari més actiu del col·lectiu indicat: l'usuari més actiu serà el que s'ha apuntat a més activitats.
        // En cas d'empat entre usuaris, s'escollirà a qualsevol usuari que compleixi els requisits.

        System.out.println("Enter the collective you want to check: ");
        String collective = keyboard.nextLine();
        if (!collective.equalsIgnoreCase("PDI") && !collective.equalsIgnoreCase("PTGAS") && !collective.equalsIgnoreCase("student")){
            System.out.println("Error, that collective does not exist");
            return;
        }
        User mostActive = null;
        for (int i = 0; i < listUser.getNumElems(); i++){
            if (listUser.getUser(i).getUserType().equalsIgnoreCase(collective)){
                if (mostActive.getNumInscriptions() < listUser.getUser(i).getNumInscriptions() || mostActive == null){
                    mostActive = listUser.getUser(i);
                }
            }
            
        }
        if (mostActive == null){
            System.out.println("There's no user in that collective");
        }
        else{
            System.out.println("Most active user: " + mostActive);
        }
    }
    //--------------------------------


}
