package validacions;

import Usuaris.PDIUser;

public class TestPDIUser {
    public static void main(String[] args) {
        testConstructor();
        testGettersSetters();
    }

    public static void testConstructor(){
        System.out.println("Testing Constructor:");
        PDIUser pdiu1 = new PDIUser("PDI", "Joan", "joan.vazquez", "Sescelades", "Enginyeria");
        System.out.println(pdiu1);
        PDIUser pdiu2 = new PDIUser("PDI", "Maria", "maria.garcia", "Sescelades", "Quimica");
        System.out.println(pdiu2);
    }

    public static void testGettersSetters(){
        PDIUser pdiu1 = new PDIUser("PDI", "Joan", "joan.vazquez", "Bellisens", "Economia");
        
        // Testing Getters
        System.out.println("User Type: " + pdiu1.getUserType());
        System.out.println("Nickname: " + pdiu1.getNickname());
        System.out.println("Email: " + pdiu1.getEmail());
        System.out.println("Campus: " + pdiu1.getCampus());
        System.out.println("Department: " + pdiu1.getDepartment());
        
        // Testing Setters
        pdiu1.setNickname("Pau");
        pdiu1.setEmail("pau.cruz");
        pdiu1.setCampus("Catalunya");
        pdiu1.setDepartment("Literatura");
        
        System.out.println("Updated Online Activity: " + pdiu1);
    }
}
