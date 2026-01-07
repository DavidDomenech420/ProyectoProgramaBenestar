package aplicacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;
import list.ActivityList;
import list.UserList;
import dades.*;

public class GraficaCalendari extends JFrame {
    private static final long serialVersionUID = 1L;
    ActivityList activities;
    private LocalDate todayDate;

    private JPanel menu;
    private JButton[][] graella;
    private JLabel instruccions;


    public GraficaCalendari (ActivityList activities, UserList users, int dimAmple, int dimAltura){
        super("ACTIVITATS ACTIVES - CALENDARI");
        this.activities = activities;

        AccioCalendari accio = new AccioCalendari();

        //Reserva d'espai de tots els botons
        graella = new JButton[dimAmple][dimAltura];

        menu = new JPanel();
        menu.setLayout(new GridLayout(dimAmple, dimAltura));

        for(int i=0; i<dimAltura; i++){
            for(int j=0; j<dimAmple; j++){
                graella[i][j] = new  JButton("\n"); //Posem \n per canviar el tamany dels botons
                graella[i][j].setBackground(Color.WHITE); //Per canviar el color dels botons
                //graella[i][j].setOpaque(true); --> Això no fa falta en Windows, però si en MAC
                graella[i][j].addActionListener(accio);
                menu.add(graella[i][j]);
            }
        }

        this.setLayout (new BorderLayout());
        this.add(menu, BorderLayout.NORTH); //A la part de dalt de la finetsra posaré el panell de botons
        this.add(instruccions, BorderLayout.SOUTH); //Especifica que posi els botons segons els punts cardinals



        //------ PASSOS DE CREACIÓ DE LA FINESTRA ------
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Per tancar la finestra, l'aplicació... Tot
        this.setSize(800, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true); //Per poder fer visible la finestra
        
    }
    
   



}
