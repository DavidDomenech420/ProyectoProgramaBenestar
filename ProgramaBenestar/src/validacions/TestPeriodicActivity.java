package validacions;
import java.time.LocalDate;
import java.time.LocalTime;

import dades.OnlineActivity;
import dades.PeriodicActivity;


public class TestPeriodicActivity {
    public static void main(String[] args) {
        testConstructor();
        testGettersSetters();
    }

    public static void testConstructor(){
        System.out.println("Testing Constructor:");
        String[] collective = {"PDI", "PTGAS"};
        PeriodicActivity act1 = new PeriodicActivity("Curs de Costura", LocalDate.of(2025, 12, 1), LocalDate.of(2025, 12, 6), collective, 50, "Dimecres", LocalTime.of(10, 30), LocalTime.of(12, 30), LocalDate.of(2026, 1, 8), 6, 76.89, "Campus Catalunya", "Tarragona");
        System.out.println(act1);
    
        PeriodicActivity act2 = new PeriodicActivity(
            "Ioga per a principiants",
            LocalDate.of(2025, 11, 10),
            LocalDate.of(2025, 11, 20),
            collective,
            25,
            "Dilluns",
            LocalTime.of(18, 0),
            LocalTime.of(19, 0),
            LocalDate.of(2026, 2, 3),
            10,
            50.0,
            "Pavelló Esportiu",
            "Reus");
        System.out.println(act2);
    }

    public static void testGettersSetters(){
        String[] collective = {"PDI"};
        PeriodicActivity act2 = new PeriodicActivity(
            "Ioga per a principiants",
            LocalDate.of(2025, 11, 10),
            LocalDate.of(2025, 11, 20),
            collective,
            25,
            "Dilluns",
            LocalTime.of(18, 0),
            LocalTime.of(19, 0),
            LocalDate.of(2026, 2, 3),
            10,
            50.0,
            "Pavelló Esportiu",
            "Reus");
        // === TEST SETTERS ===
        act2.setDayOfActivity("Dimarts");
        act2.setInicialTime(LocalTime.of(17, 0));
        act2.setFinalTime(LocalTime.of(18, 30));
        act2.setInicialDate(LocalDate.of(2026, 2, 10));
        act2.setWeeksOfActivity(12);
        act2.setPriceActivity(65.5);
        act2.setCenterName("Centre Cívic Migjorn");
        act2.setCityName("Valls");

        // === TEST GETTERS ===
        System.out.println("Day of Activity: " + act2.getDayOfActivity());
        System.out.println("Inicial Time: " + act2.getInicialTime());
        System.out.println("Final Time: " + act2.getFinalTime());
        System.out.println("Inicial Date: " + act2.getInicialDate());
        System.out.println("Weeks of Activity: " + act2.getWeeksOfActivity());
        System.out.println("Price: " + act2.getPriceActivity());
        System.out.println("Center: " + act2.getCenterName());
        System.out.println("City: " + act2.getCityName());
    }
}
