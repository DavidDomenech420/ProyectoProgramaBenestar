package aplicació;
import dades.*;

public class AppProgramaBenestar {
    public static void main(String[] args) throws Exception {
  
    }

    
    public static void mostraMenu(){
        System.out.println("\n\nOPCIONS DEL MENU: ");
    }

    public static void verifyConstructor(){
        PeriodicActivity p1 = new PeriodicActivity("TallerMandales", "Dimecres", LocalTime.of(18, 30), LocalTime.of(20, 45), new Date(2025, 11, 30), 3, 65, 43.67, "Campus Catalunya", collective, new Date(2025, 11, 27), new Date(2025, 11, 29));
        System.out.println(p1);
    }


}
