package dades;

import Usuaris.User;

public class Inscriptions {
    private String nickName;
    private int assessment;
    
    public Inscriptions(String nickName, int assessment) {
        this.nickName = nickName;
        this.assessment = assessment;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAssessment() {
        return assessment;
    }
    
    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return "Inscriptions [nickName=" + nickName + ", assessment=" + assessment + "]";
    }

}
