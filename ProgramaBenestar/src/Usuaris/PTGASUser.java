package Usuaris;

/**
 * @author Autoria de la classe: Sandra Serra Férriz i Júlia Alquézar Duran
 */
public class PTGASUser extends User{
    private String campus;
    

    // Constructor
    public PTGASUser(String userType, String nickname, String email, String campus) {
        super(userType, nickname, email);
        this.campus = campus;
    }

    // Getters and Setters
    public String getCampus() {
        return campus;
    }
    public void setCampus(String campus) {
        this.campus = campus;
    }

    public PTGASUser copy() {
        return new PTGASUser(this.getUserType(), this.getNickname(), this.getEmail(), this.campus);
    }

    @Override
    public String toString() {
        return "PTGAS User [Nickname: " + getNickname() + ", Email: " + getEmail() + "@urv.cat, Campus: " + campus + "]";
    }
}
