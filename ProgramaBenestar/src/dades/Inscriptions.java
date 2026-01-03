package dades;

import java.io.Serializable;

import Usuaris.User;

public class Inscriptions implements Serializable{
    private User user;
    private int assessment;
    
    public Inscriptions(User user) {
        this.user = user;
        this.assessment = 0;
    }

    public String getNickName() {
        return user.getNickname();
    }

    public void setNickName(User user) {
        this.user = user;
    }

    public int getAssessment() {
        return assessment;
    }
    
    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return "Inscriptions [User=" + user + ", assessment=" + assessment + "]";
    }

    public User getUser(){
        return user;
    }

}
