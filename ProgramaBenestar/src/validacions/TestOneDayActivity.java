package validacions;
import dades.OneDayActivity;
import java.time.LocalDate;
import java.time.LocalTime;

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
        OneDayActivity oda1 = new OneDayActivity("OneDay","Trip to the mountains", LocalDate.of(2024, 10, 1), LocalDate.of(2024, 9, 15), collectives,30, "Barcelona", LocalDate.of(2024, 10, 5), LocalTime.of(9, 0), LocalTime.of(17, 0), 50.0);
        System.out.println(oda1);
        OneDayActivity oda2 = new OneDayActivity("OneDay","Trip to the beach", LocalDate.of(2026, 8, 1), LocalDate.of(2024, 8, 10), collectives,25, "Sitges", LocalDate.of(2024, 8, 15), LocalTime.of(10, 0), LocalTime.of(18, 0), 30.0);
        System.out.println(oda2);
    }

    public static void testGettersSetters(){
        String[] collectives = {"PDI", "PTGAS"};
        OneDayActivity oda2 = new OneDayActivity("OneDay", "City Tour", LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 10), collectives, 20, "Madrid", LocalDate.of(2024, 10, 5), LocalTime.of(9, 0), LocalTime.of(17, 0), 40.0);
        
        // Testing Getters
        System.out.println("Activity Name: " + oda2.getActivityName());
        System.out.println("Collectives: " + oda2.getCollectiveString());
        System.out.println("Start Date Inscriptions: " + oda2.getStartDateInscriptions());
        System.out.println("Finish Date Inscriptions: " + oda2.getFinishDateInscriptions());
        System.out.println("Inscriptions: " + oda2.getInscriptions());
        System.out.println("City: " + oda2.getCity());
        System.out.println("Day: " + oda2.getDay());
        System.out.println("Start Time: " + oda2.getStartTime());
        System.out.println("Finish Time: " + oda2.getFinishTime());
        System.out.println("Price: " + oda2.getPrice());
        
        // Testing Setters
        oda2.setActivityName("Updated City Tour");
        oda2.setCity("Seville");
        oda2.setStartDateInscriptions(LocalDate.of(2024, 10, 5));
        oda2.setFinishDateInscriptions(LocalDate.of(2024, 10, 15));
        oda2.setStartTime(LocalTime.of(10, 0));
        oda2.setFinishTime(LocalTime.of(18, 0));
        oda2.setPrice(45.0);
        
        System.out.println("Updated One Day Activity: " + oda2);
    }

    
    public static void testAddInscription(){
        String [] collectives = {"PDI", "Estudiant"};
        OneDayActivity oda3 = new OneDayActivity("One Day", "Beach Day", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 10), collectives, 15, "Valencia", LocalDate.of(2024, 7, 5), LocalTime.of(10, 0), LocalTime.of(18, 0), 20.0);
        System.out.println("Before adding inscriptions: " + oda3.getInscriptions());
        // Add inscriptions and test behavior
        User user1 = new PDIUser("PDI", "john_doe", "john.doe", "Sescelades", "Informatica");
        oda3.addInscriptions(user1);
        System.out.println("After adding John Doe: " + oda3.getInscriptions());
        
    }
}
