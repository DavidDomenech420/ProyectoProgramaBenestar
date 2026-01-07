package aplicacio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AccioVeureDia implements ActionListener{
    private GraficaCalendari calendar;
    private int day;

    public AccioVeureDia(GraficaCalendari calendar, int day){
        this.calendar = calendar;
        this.day = day;
    }
    public void actionPerformed(ActionEvent event){
        calendar.changeDateTemp(day);
    }
}
