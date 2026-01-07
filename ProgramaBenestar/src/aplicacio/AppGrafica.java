package aplicacio;

import javax.swing.*; //importar per a que funcioni el 'extends JFrame'

import Usuaris.PDIUser;
import Usuaris.PTGASUser;
import Usuaris.StudentUser;
import Usuaris.User;
import dades.Activities;
import dades.Inscriptions;
import dades.OneDayActivity;
import dades.OnlineActivity;
import dades.PeriodicActivity;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

import list.ActivityList;
import list.UserList;




/**
 * @author Autoria de la classe: Sandra Serra Férriz i Júlia Alquézar Duran
 */
public class AppGrafica extends JFrame {
    private static final long serialVersionUID = 1L;
    ActivityList activities;
    UserList users;
    private JPanel menu;

    /**
     * Finestra principal de la interfície gràfica (GUI)
     * @param activities Llista que conté totes les activitats del sistema.
     * @param users Llista que conté tots els usuaris registrats.
     * genera un menú amb botons que permeten a l'usuari accedir a totes les funcionalitats de gestió d'activitats i usuaris
     */
    public AppGrafica (ActivityList activities, UserList users){
        super("PROGRAMA BENESTAR URV");
        
        AccioBotons accio = new AccioBotons(activities, users);

        menu = new JPanel();
        menu.setLayout(new GridLayout(0, 1));

        //------ BOTONS ------
        
        JButton button1 = new JButton("Informació sobre la data actual");
        button1.addActionListener(accio);
        menu.add(button1);

        JButton button2 = new JButton("Informació de les dades d'una llista");
        button2.addActionListener(accio);
        menu.add(button2);

        JButton button3 = new JButton("Informació de les activitats en període d'inscripció");
        button3.addActionListener(accio);
        menu.add(button3);

        JButton button4 = new JButton("Informació d'activitats amb classe en la data actual");
        button4.addActionListener(accio);
        menu.add(button4);

        JButton button5 = new JButton("Activitats actives en la data actual");
        button5.addActionListener(accio);
        menu.add(button5);

        JButton button6 = new JButton("Activitats amb places disponibles");
        button6.addActionListener(accio);
        menu.add(button6);

        JButton button7 = new JButton("Informació d'una activitat");
        button7.addActionListener(accio);
        menu.add(button7);

        JButton button8 = new JButton("Informació d'un usuari");
        button8.addActionListener(accio);
        menu.add(button8);

        JButton button9 = new JButton("Activitats on estàs inscrit");
        button9.addActionListener(accio);
        menu.add(button9);

        JButton button10 = new JButton("Inscripció a una activitat");
        button10.addActionListener(accio);
        menu.add(button10);

        JButton button11 = new JButton("Usuaris inscrits en activitats i usuaris inscrits en llista d'espera");
        button11.addActionListener(accio);
        menu.add(button11);

        JButton button12 = new JButton("Eliminació d'usuari d'una activitat");
        button12.addActionListener(accio);
        menu.add(button12);

        JButton button13 = new JButton("Afegir una nova activitat d'un dia");
        button13.addActionListener(accio);
        menu.add(button13);

        JButton button14 = new JButton("Afegir una nova activitat periòdica");
        button14.addActionListener(accio);
        menu.add(button14);

        JButton button15 = new JButton("Afegir una nova activitat en línia");
        button15.addActionListener(accio);
        menu.add(button15);

        JButton button16 = new JButton("Valoració d'una activitat");
        button16.addActionListener(accio);
        menu.add(button16);

        JButton button17 = new JButton("Resum de valoracions de les activitats");
        button17.addActionListener(accio);
        menu.add(button17);

        JButton button18 = new JButton("Resum de valoracions d'un usuari");
        button18.addActionListener(accio);
        menu.add(button18);

        JButton button19 = new JButton("MItjanes de valoracions dels col·lectius");
        button19.addActionListener(accio);
        menu.add(button19);

        JButton button20 = new JButton("Usuari més actiu d'un col·lectiu");
        button20.addActionListener(accio);
        menu.add(button20);

        JButton button21 = new JButton("Baixa d'activitats");
        button21.addActionListener(accio);
        menu.add(button21);

        JButton button22 = new JButton("Guardar i Sortir");
        button22.addActionListener(accio);
        button22.setBackground(Color.ORANGE);
        menu.add(button22);



        //------ PASSOS DE CREACIÓ DE LA FINESTRA ------
        //Si hi ha molts botons, els podrem veure tots fent scroll
        JScrollPane scroll = new JScrollPane(menu);
        this.add(scroll, BorderLayout.CENTER); 

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Per tancar la finestra, l'aplicació... Tot
        this.setSize(800, 700); //Ajusta la mida de finestra al contingut
        this.setLocationRelativeTo(null);
        this.setVisible(true); //Per poder fer visible la finestra

    }
}