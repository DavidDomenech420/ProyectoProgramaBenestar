package validacions;

import Usuaris.PTGASUser;

public class TestPDGASUser {
    public static void main(String[] args) {
        testConstructor();
        testGettersSetters();
    }

    public static void testConstructor(){
        System.out.println("Testing Constructor:");
        PTGASUser ptgasu1 = new PTGASUser("PTGAS", "Joan", "joan.vazquez", "Sescelades");
        System.out.println(ptgasu1);
        PTGASUser ptgasu2 = new PTGASUser("PTGAS", "Maria", "maria.garcia", "Catalunya");
        System.out.println(ptgasu2);
    }

    public static void testGettersSetters(){
        PTGASUser ptgasu1 = new PTGASUser("PTGAS", "Joan", "joan.vazquez", "Bellisens");
        
        // Testing Getters
        System.out.println("User Type: " + ptgasu1.getUserType());
        System.out.println("Nickname: " + ptgasu1.getNickname());
        System.out.println("Email: " + ptgasu1.getEmail());
        System.out.println("Campus: " + ptgasu1.getCampus());
        
        // Testing Setters
        ptgasu1.setNickname("Pau");
        ptgasu1.setEmail("pau.cruz");
        ptgasu1.setCampus("Catalunya");
        
        System.out.println("Updated Online Activity: " + ptgasu1);
    }
}
