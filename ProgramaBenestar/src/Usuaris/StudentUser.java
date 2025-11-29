package Usuaris;

public class StudentUser extends User{
    private String degree;
    private int firstYear;

    // Constructor
    public StudentUser(String userType, String nickname, String email, String degree, int firstYear) {
        super(userType, nickname, email);
        this.degree = degree;
        this.firstYear = firstYear;
    }

    // Getters and Setters
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public int getFirstYear() {
        return firstYear;
    }
    public void setFirstYear(int firstYear) {
        this.firstYear = firstYear;
    }

    @Override
    public User copy() {
        return new StudentUser(this.getUserType(), this.getNickname(), this.getEmail(), this.degree, this.firstYear);
    }
    
    @Override
    public String toString() {
        return "Student User [Nickname: " + getNickname() + ", Email: " + getEmail() + "@estudiants.urv.cat, Degree: " + degree + ", First Year: " + firstYear + "]";
    }
    
}
