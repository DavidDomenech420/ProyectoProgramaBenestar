package validacions;
import dades.OneDayActivity;
import java.time.LocalDate;

import Usuaris.PDIUser;
import Usuaris.User;

public class TestOneDayActivity {
    public static void main(String[] args) {
        testConstructor();
        testGettersSetters();
        testAddInscription();
    }


    public static void testConstructor(){
        System.out.println("Testing Constructor:");
        String[] collectives = {"PDI", "PTGAS"};
        OneDayActivity oda1 = new OneDayActivity("Trip to the mountains", LocalDate.of(2024, 10, 1), LocalDate.of(2024, 9, 15), collectives,30, "Barcelona");
        System.out.println(oda1);
        OneDayActivity oda2 = new OneDayActivity("Trip to the beach", LocalDate.of(2026, 8, 1), LocalDate.of(2024, 8, 10), collectives,25, "Sitges");
        System.out.println(oda2);
    }

    public static void testGettersSetters(){
        String[] collectives = {"PDI", "PTGAS"};
        OneDayActivity oda2 = new OneDayActivity("City Tour", LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 10), collectives, 20, "Madrid");
        
        // Testing Getters
        System.out.println("Activity Name: " + oda2.getActivityName());
        System.out.println("Start Date Inscriptions: " + oda2.getStartDateInscriptions());
        System.out.println("Finish Date Inscriptions: " + oda2.getFinishDateInscriptions());
        System.out.println("City: " + oda2.getCity());
        
        // Testing Setters
        oda2.setActivityName("Updated City Tour");
        oda2.setCity("Seville");
        oda2.setStartDateInscriptions(LocalDate.of(2024, 10, 5));
        oda2.setFinishDateInscriptions(LocalDate.of(2024, 10, 15));
        
        System.out.println("Updated One Day Activity: " + oda2);
    }

    public static void testAddInscription(){
        String [] collectives = {"PDI", "Estudiant"};
        OneDayActivity oda3 = new OneDayActivity("Beach Day", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 10), collectives, 15, "Valencia");
        System.out.println("Before adding inscriptions: " + oda3.getInscriptionsString());
        // Add inscriptions and test behavior
        User user1 = new PDIUser("PDI", "john_doe", "john.doe", "Sescelades", "Informatica");
        oda3.addInscription(user1);
        System.out.println("After adding John Doe: " + oda3.getInscriptionsString());
        
    }
}
