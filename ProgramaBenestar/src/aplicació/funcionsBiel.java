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
    public static void option8(String Name, UserList List){
        //8. Mostrar informació d'usuari: informació detallada a partir del nom d'aquest.
        User aux = List.getUser(Name);
        System.out.println(aux);
    }
    //--------------------------------
}
