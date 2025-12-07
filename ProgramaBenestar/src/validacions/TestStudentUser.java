package validacions;



import Usuaris.StudentUser;

public class TestStudentUser {
    public static void main(String[] args) {
        testConstructor();
        testGettersSetters();
    }

    public static void testConstructor(){
        System.out.println("Testing Constructor:");
        StudentUser su1 = new StudentUser("Student", "Joan", "joan.vazquez", "GEI", 2);
        System.out.println(su1);
        StudentUser su2 = new StudentUser("Student", "Maria", "maria.garcia", "BioTech", 3);
        System.out.println(su2);
    }

    public static void testGettersSetters(){
        StudentUser su1 = new StudentUser("Student", "Joan", "joan.vazquez", "GEI", 2);
        
        // Testing Getters
        System.out.println("User Type: " + su1.getUserType());
        System.out.println("Nickname: " + su1.getNickname());
        System.out.println("Email: " + su1.getEmail());
        System.out.println("Degree: " + su1.getDegree());
        System.out.println("Couse Year: " + su1.getFirstYear());
        
        // Testing Setters
        su1.setNickname("Pau");
        su1.setEmail("pau.cruz");
        su1.setDegree("ADE");
        su1.setFirstYear(1);
        
        System.out.println("Updated Online Activity: " + su1);
    }
}
