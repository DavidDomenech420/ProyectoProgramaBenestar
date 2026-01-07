package aplicacio;

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

//Aquesta classe és la responsable de tractar els esdeveniments dels botons
public class AccioCalendari implements ActionListener {
    private ActivityList activities;
    private UserList users;

    //Si no es crea el constructor, la classe no té accés a les dades
    public AccioCalendari (ActivityList activities, UserList users){
        this.activities = activities;
        this.users = users;
    }


    public void actionPerformed (ActionEvent event) {
        String option = event.getActionCommand(); //S'ha d'utilitzar getActionCommand per saber quina opció s'ha escollit
        String day = event.getActionCommand(); //S'ha d'utilitzar getActionCommand per saber quin dia s'ha escollit

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
    
    
        //
    
    
    
    }






}
