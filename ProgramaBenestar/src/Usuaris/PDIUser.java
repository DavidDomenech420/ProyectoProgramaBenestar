package Usuaris;

/**
 * @author Autoria de la classe: David Doménech Aguilera
 */
public class PDIUser extends PTGASUser{
    private String department;

    // Constructor
    public PDIUser(String userType, String nickname, String email, String campus, String department) {
        super(userType, nickname, email, campus);
        this.department = department;
    }

    // Getters and Setters
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public PDIUser copy() {
        return new PDIUser(this.getUserType(), this.getNickname(), this.getEmail(), this.getCampus(), this.department);
    }
    
    @Override
    public String toString() {
        return "PDI User [Nickname: " + getNickname() + ", Email: " + getEmail() + "@urv.cat, Campus: " + getCampus() + ", Department: " + department + "]";
    }
    
}
