package list;

import Usuaris.User;

public class UserList {
    User[] list;
    int nElem;
    
    public UserList(int n){
        list = new User[n];
        nElem = 0;
    }

    public void addUser (User user){
        if (nElem < list.length) {
            list[nElem] = user;
            nElem++;
        }
        else {
            System.out.println("No more space in the list");
        }
    }

    @Override
    public String toString() {
        String aux = "User List: \n";
        for (int i = 0; i < nElem; i++) {
            aux += "1 - " + list[nElem] + "\n";
        }
        return aux;
    }

    // private int getNElem(){
    //     return nElem;
    // }

    public User getUser(int pos){
        return list[pos];
    }
    
    public User getUser(String name){
        User user = null;
        for (int i = 0; i < nElem; i++) {
            if (list[i].getNickname().equalsIgnoreCase(name)) {
                user = list[i];
            }
        }

        return user;
    }
    
}
