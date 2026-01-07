package aplicacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.TextStyle;
import java.time.LocalDate;
import list.ActivityList;
import list.UserList;
import dades.*;

public class GraficaCalendari extends JFrame {
    private static final long serialVersionUID = 1L;
    ActivityList activities;
    UserList users;
    private LocalDate actualDate;
    private JPanel monthDays;
    private JLabel monthAndYear;  //JLabel --> etiqueta informativa (en aquest cas, per dir el mes i any on ens trobem)

    /**
     * Constructor amb paràmetres
     * 
     * @param activities llista d'activitats
     * @param dimAmple  dimensió d'ample
     * @param dimAltura dimensió d'altura
     */
    public GraficaCalendari (ActivityList activities, UserList users, int dimAmple, int dimAltura){
        super("ACTIVITATS ACTIVES - CALENDARI");
        this.activities = activities;
        this.users = users;
        this.actualDate = AppProgramaBenestar.usedDate;

        this.setSize(800, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Tanca la finestra i torna al menu
        this.setLayout(new BorderLayout());

        //-------- PARTE SUPERIOR (MES I ANY) --------
        JPanel topPanel = new JPanel(new FlowLayout()); //FlowLayout --> elements un darrere l'altre
        JButton beforeButton = new JButton("<-- Anterior");
        JButton afterButton = new JButton("Següent -->");
        monthAndYear = new JLabel();

        //Accions al botons
        beforeButton.addActionListener(new AccioCanviMes(this, -1)); //Passem al mes anterior
        afterButton.addActionListener(new AccioCanviMes(this, 1)); //Passem al mes següent

        topPanel.add(beforeButton);
        topPanel.add(monthAndYear);
        topPanel.add(afterButton);
        this.add(topPanel, BorderLayout.NORTH);
        //--------------------------------------------


        //-------- PARTE CENTRAL (DIES) --------
        JPanel centralPanel = new JPanel(new BorderLayout()); //BorderLayout --> especifico les posicions dels elements amb els punts cardinals
        JPanel days = new JPanel(new GridLayout(1, 7)); //GridLayout --> distribueix els elements d'una matriu en cel·les iguals
        String[] daysOfWeek = {"Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres", "Dissabte", "Diumenge"};
        
        for (int i=0; i<daysOfWeek.length; i++){
            String nameDay = daysOfWeek[i];

            JLabel tagDay = new JLabel(nameDay, SwingConstants.CENTER); //SwingConstants.CENTER --> constant que li diu a l'eqtiqueta com posar el text
            tagDay.setOpaque(true);
            tagDay.setBackground(Color.LIGHT_GRAY);
            tagDay.setBorder(BorderFactory.createEtchedBorder()); //Borde per resaltar i decorar
            days.add(tagDay);
        }
        centralPanel.add(days, BorderLayout.NORTH);

        monthDays = new JPanel(new GridLayout(6, 7));
        centralPanel.add(monthDays, BorderLayout.CENTER);
        this.add(centralPanel, BorderLayout.CENTER);

        printCalendar();
        this.setVisible(true);
        //--------------------------------------------
    }
    

    /**
     * printCalendar: funció per mostrar el calendari
     * 
     */
    private void printCalendar(){
        monthDays.removeAll(); //Esborrem l'anterior per posar el nou

        //ACTUALITZACIÓ DEL MES I (A LA PART SUPERIOR)
        int numberMonth = actualDate.getMonthValue();
        String name = monthName(numberMonth);
        monthAndYear.setText(name+ " " +actualDate.getYear());
        
        //Mirem quants dies té cada més que representarem
        //La variable 'yearMonth' guardarà un  mes i un any per saber després si es tracta d'un mes de 30, 31, etc
        YearMonth yearMonth = YearMonth.of(actualDate.getYear(), actualDate.getMonth());
        int daysOfTheMonth = yearMonth.lengthOfMonth();

        //Ara hem de calcular per quin dia comença el mes (dilluns, dimarts, etc), per poder ordenar bé els dies en el calendari
        LocalDate firstDay = actualDate.withDayOfMonth(1); //Obliga a posar la data com si fos dia 1 de mes, per ordenar millor
        int startDay = firstDay.getDayOfWeek().getValue(); //retorna un número de dia (1 = dilluns ; 2 = dimarts ; etc)

        //Llavors si posa 3 (dimecres), per exemple, que el dia 1 és dimecres, llavors es queden buides les caselles 'dilluns' i 'dimarts' i el mes comença per dimecres
        int inUseDays = 0; //Guardarem quants espais hi ha abans del dia 1 per posar-ho
        for(int i=1; i<startDay; i++){
            monthDays.add(new JLabel("")); //Espai
            inUseDays++;
        }
    
        for(int j=1; j<=daysOfTheMonth; j++){
            JButton button = new JButton(String.valueOf(j));
            button.setBackground(Color.WHITE);

            //Comparació amb la data actual
            if(actualDate.getYear() == AppProgramaBenestar.usedDate.getYear() && actualDate.getMonth() == AppProgramaBenestar.usedDate.getMonth() && j == AppProgramaBenestar.usedDate.getDayOfMonth()){
                button.setBackground(Color.CYAN); //Marquen 'avui', actualDate
            }

            //Per veure que hi ha al dia
            button.addActionListener(new AccioVeureDia(this, j));

            monthDays.add(button);
            inUseDays++;
        }
        while (inUseDays < 42){
            monthDays.add(new JLabel(""));
            inUseDays++;
        }

        //Actualización de pantalla per a que noo es quedi només a memòria (conceptes nous)
        monthDays.revalidate(); //Recalcula posicions
        monthDays.repaint(); //Mostra canvis
    }

    /**
     * changeMonth: funció per canviar de data segons com ens movem de mes a mes
     * @param value enter que val -1 si anem al mes anterior o 1 si anem al següent
     */
    public void changeMonth(int value){
        //Actualització de la variable de la data
        this.actualDate = this.actualDate.plusMonths(value);
        printCalendar(); //Actualitza pantalla
    }

    /**
     * changeDateTemp: funció que 'canvia' la data temporalmente, posa com a data del sistema la data del dia clickat
     * @param day enter del dia que clickem
     */
    public void changeDateTemp(int day){
        //1- Guardar data original per no perdre-la
        LocalDate originalDate = AppProgramaBenestar.usedDate;

        //2- Creació de data temporal
        LocalDate buttonDate = LocalDate.of(actualDate.getYear(), actualDate.getMonth(), day);

        //3- 'Canvi' de dates
        AppProgramaBenestar.usedDate = buttonDate;

        //4- Acció per mostrar activitats actives
        AccioBotons.activeActivities(activities, users);

        //5- Tornar a la data original
        AppProgramaBenestar.usedDate = originalDate;
    }


    /**
     * monthName: funció auxiliar per mostrar el nom del mes
     * @param numberOfMonth número de mes
     * @return nom del mes segons el número d'aquest
     */
    private String monthName (int numberOfMonth){
        switch(numberOfMonth){
            case 1:
                return "GENER";
            case 2:
                return "FEBRER";
            case 3:
                return "MARÇ";
            case 4:
                return "ABRIL";
            case 5:
                return "MAIG";
            case 6:
                return "JUNY";
            case 7:
                return "JULIOL";
            case 8:
                return "AGOST";
            case 9:
                return "SETEMBRE";
            case 10:
                return "OCTUBRE";
            case 11:
                return "NOVEMBRE";
            case 12:
                return "DESEMBRE";
            default:
                return "ERROR";
        }
    }
}
