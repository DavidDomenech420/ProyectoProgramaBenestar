package aplicacio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AccioCanviMes implements ActionListener{
    private GraficaCalendari calendar;
    private int value;

    public AccioCanviMes(GraficaCalendari calendar, int value){
        this.calendar = calendar;
        this.value = value;
    }
    public void actionPerformed(ActionEvent event){
        calendar.changeMonth(value);
    }
}
