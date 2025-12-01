package Usuaris;
public abstract class User {
    private String userType;
    private String nickname;
    private String email;

    // Constructor
    public User(String userType, String nickname, String email) {
        this.userType = userType;
        this.nickname = nickname;
        this.email = email;
    }

    // Getters and Setters

    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // public User copy() {
    //     return new User(this.userType, this.nickname, this.email);
    // }

    @Override
    public String toString() {
        return "User [Nickname: " + nickname + ", Email: " + email + "]";
    }
}