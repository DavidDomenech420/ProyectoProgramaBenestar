package validacions;

import java.time.LocalDate;

import dades.OnlineActivity;

public class TestOnlineActivity {
    public static void main(String[] args) {
        testConstructor();
        testGettersSetters();
    }

    public static void testConstructor(){
        System.out.println("Testing Constructor:");
        String[] collectives = {"PDI", "PTGAS"};
        OnlineActivity oda1 = new OnlineActivity("Online", "Hacking Course", collectives, LocalDate.of(2024, 10, 1), LocalDate.of(2024, 9, 15), LocalDate.of(2024, 9, 20), LocalDate.of(2024, 9, 30), "hackingcourse.com");
        System.out.println(oda1);
        OnlineActivity oda2 = new OnlineActivity("Online", "Java Course", collectives, LocalDate.of(2024, 8, 10), LocalDate.of(2024, 7, 20), LocalDate.of(2025, 10, 11), LocalDate.of(2026, 1, 11), "javaProject.es");
        System.out.println(oda2);
    }

    public static void testGettersSetters(){
        String[] collectives = {"PDI", "PTGAS"};
        OnlineActivity oda2 = new OnlineActivity("Online", "Hacking Course", collectives, LocalDate.of(2024, 10, 1), LocalDate.of(2024, 9, 15), LocalDate.of(2024, 9, 20), LocalDate.of(2024, 10, 20), "hackingcourseURV.cat");
        
        // Testing Getters
        System.out.println("Activity Name: " + oda2.getActivityName());
        System.out.println("Start Date Inscriptions: " + oda2.getStartDateInscriptions());
        System.out.println("Finish Date Inscriptions: " + oda2.getFinishDateInscriptions());
        System.out.println("Start Date Activity: " + oda2.getStartDateActivity());
        System.out.println("Finsh Date Activity: " + oda2.getFinishDateActivity());
        System.out.println("Course Link: " + oda2.getLinkCourse());
        
        // Testing Setters
        oda2.setActivityName("JavaScript Course");
        oda2.setStartDateActivity(LocalDate.of(2025, 1, 26));
        oda2.setFinishDateActivity(LocalDate.of(2025, 2, 26));
        oda2.setLinkCourse("myjavascript.com");
        oda2.setStartDateInscriptions(LocalDate.of(2024, 10, 5));
        oda2.setFinishDateInscriptions(LocalDate.of(2024, 10, 15));
        
        System.out.println("Updated Online Activity: " + oda2);
    }
}
