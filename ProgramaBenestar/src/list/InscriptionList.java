package list;

import java.time.LocalDate;

import Usuaris.User;
import dades.Inscriptions;

public class InscriptionList {
    Inscriptions[] list;
    int numElems;

    public InscriptionList(int n) {
        list = new Inscriptions[n];
        numElems = 0;
    }

    public void addNewInscription(Inscriptions newInscription){
        if(numElems < list.length){
            list[numElems] = newInscription;
            numElems++;
        }
    }

    public void removeInscription(User newUser){
        String name = newUser.getNickname();
        for(int i=0; i<numElems; i++){
            if((list[i].getNickName()).equalsIgnoreCase(name)){
                for(int j=i; j<numElems; j++){
                    list[j] = list[j+1];
                }
                list[numElems-1] = null; //No podem posar j, perquè fora del 'for' ja no existeix
                numElems--;
            }
        }
    }


/* 
    public void addInscription(User member){
        char contains = 'N';
        for (int i = 0; i < collective.length; i++) {
            if (member.getUserType().equals(collective[i])) {
                contains = 'Y';
            }
        }
        if (contains == 'N') {
            System.out.println("The member's user type is not allowed for this activity.");
            return;
        }
        if (finishDateInscriptions.isBefore(LocalDate.now())) {
            System.out.println("The inscription period has ended. Cannot add inscription.");
            return;
        }
        if (startDateInscriptions.isAfter(LocalDate.now())) {
            System.out.println("The inscription period has not started yet. Cannot add inscription.");
            return;
        }
        if (numInscriptions < inscriptions.length){
            inscriptions[numInscriptions] = member;
            numInscriptions++;
        }
        else {
            System.out.println("Inscriptions are full. Cannot add more members.");
        }
    }

    public void removeInscription(User member){
        for (int i = 0; i < numInscriptions; i++) {
            if (inscriptions[i].getNickname().equals(member.getNickname())) {
                // Movem els elements cap a l'esquerra
                for (int j = i; j < numInscriptions - 1; j++) {
                    inscriptions[j] = inscriptions[j + 1];
                }
                inscriptions[numInscriptions - 1] = null;
                numInscriptions--;
                return;
            }
        }
        System.out.println("Member not found in inscriptions.");
    }
*/
    
}
