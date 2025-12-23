package aplicació;

import java.time.LocalDate;
import java.time.LocalTime;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import dades.Activities;
import dades.OneDayActivity;
import dades.OnlineActivity;
import dades.PeriodicActivity;
import list.ActivityList;
import list.UserList;
import Usuaris.PDIUser;
import Usuaris.PTGASUser;
import Usuaris.StudentUser;
import Usuaris.User;

public class AppProgramaBenestar {
    public static void main(String[] args) throws Exception {
        // Inicialitzem les variables per la llista d'activitats
        ActivityList activities = new ActivityList(300);
        // Mirem si existeix el fitxer, si es aixi guardem la informació dels fitxers a les variables corresponents, si no estaran buides
        try {
            BufferedReader fileRead=new BufferedReader(new FileReader("data.txt"));
            String frase="";
            frase=fileRead.readLine(); //Llegim capçalera 1
            frase=fileRead.readLine(); //Llegim capçalera 2
            frase=fileRead.readLine(); //Llegim capçalera 3

            //Ara llegim primera línia amb informació:
            frase=fileRead.readLine();
            
            Activities activity = null;
            while (frase != null) {
                // Separem la frase per els ; i mirem el primer element
                String[] frasesParts = frase.split(";");
                String activityType = frasesParts[0];
                // Agafem les primeres 6 parts ja que son iguals sense importar l'activitat
                String activityName = frasesParts[1];
                LocalDate startInscriptionDate = LocalDate.of(Integer.parseInt(frasesParts[2].split("-")[0]), Integer.parseInt(frasesParts[2].split("-")[1]), Integer.parseInt(frasesParts[2].split("-")[2]));
                LocalDate finishInscriptionDate = LocalDate.of(Integer.parseInt(frasesParts[3].split("-")[0]), Integer.parseInt(frasesParts[3].split("-")[1]), Integer.parseInt(frasesParts[3].split("-")[2]));;
                String[] collectives = frasesParts[4].split(",");
                int maxInscriptions = Integer.parseInt(frasesParts[5]);
                
                // Segons el activityType mirarem uns parametres o altres
                if (activityType.equalsIgnoreCase("OneDay")) {
                    String city = frasesParts[6];
                    LocalDate day = LocalDate.of(Integer.parseInt(frasesParts[7].split("-")[0]), Integer.parseInt(frasesParts[7].split("-")[1]), Integer.parseInt(frasesParts[7].split("-")[2]));;
                    LocalTime startTime = LocalTime.of(Integer.parseInt(frasesParts[8].split(":")[0]), Integer.parseInt(frasesParts[8].split(":")[1]));
                    LocalTime finishTime = LocalTime.of(Integer.parseInt(frasesParts[9].split(":")[0]), Integer.parseInt(frasesParts[9].split(":")[1]));
                    double price = Double.parseDouble(frasesParts[10]);
                    activity = new OneDayActivity(activityType, activityName, startInscriptionDate, finishInscriptionDate, collectives, maxInscriptions, city, day, startTime, finishTime, price);
                }
                else if (activityType.equalsIgnoreCase("Periodic")){
                    String day = frasesParts[6];
                    LocalTime startTime = LocalTime.of(Integer.parseInt(frasesParts[7].split(":")[0]), Integer.parseInt(frasesParts[7].split(":")[1]));
                    LocalTime finishTime = LocalTime.of(Integer.parseInt(frasesParts[8].split(":")[0]), Integer.parseInt(frasesParts[8].split(":")[1]));
                    LocalDate inicialDate = LocalDate.of(Integer.parseInt(frasesParts[9].split("-")[0]), Integer.parseInt(frasesParts[9].split("-")[1]), Integer.parseInt(frasesParts[9].split("-")[2]));;
                    int weeksOfActivity = Integer.parseInt(frasesParts[10]);
                    double price = Double.parseDouble(frasesParts[11]);
                    String centerName = frasesParts[12];
                    String cityName = frasesParts[13];
                    activity = new PeriodicActivity(activityType, activityName, startInscriptionDate, finishInscriptionDate, collectives, maxInscriptions, day, startTime, finishTime, inicialDate, weeksOfActivity, price, centerName, cityName);
                }
                else if (activityType.equalsIgnoreCase("Online")){
                    LocalDate startDateActivity = LocalDate.of(Integer.parseInt(frasesParts[6].split("-")[0]), Integer.parseInt(frasesParts[6].split("-")[1]), Integer.parseInt(frasesParts[6].split("-")[2]));;
                    LocalDate finishDateActivity = LocalDate.of(Integer.parseInt(frasesParts[7].split("-")[0]), Integer.parseInt(frasesParts[7].split("-")[1]), Integer.parseInt(frasesParts[7].split("-")[2]));;
                    String linkCourse = frasesParts[8];
                    activity = new OnlineActivity(activityType, activityName, collectives, startInscriptionDate, finishInscriptionDate, startDateActivity, finishDateActivity, linkCourse);
                }
                activities.addActivity(activity);
                frase = fileRead.readLine();
            }
            fileRead.close();
        } catch (FileNotFoundException e) {
            System.out.println("No hi ha cap fitxer creat actualment");
        }
 


        // ------ Fitxer .ser - usuaris -----
        UserList usersList = new UserList(300);

        try{
            ObjectInputStream fileUser = new ObjectInputStream(new FileInputStream("users.ser")); // Fitxer serialitzat
            System.out.println("LLEGINT ...");
            while(true){ //Quan arribi a final de fitxer sortirà l'excepció EOF Exception
                User user = (User) fileUser.readObject();
                usersList.addUser(user);
            }
        } catch(EOFException e){ //Final de fitxer
            System.out.println("Usuaris guardats correctament");
        } catch (FileNotFoundException e){
            System.out.println("Fitxer no existent.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //1- IOException: Error de lectura del disc. El ObjectInpuStream falla. El fitxer no és vàlid.
        //2- ClassNotFoundException: Error de classe.

        try{
            ObjectOutputStream fileUser = new ObjectOutputStream(new FileOutputStream("users.ser")); //Crearia un fitxer nou amb ek nom users, però com ja hi ha un de creat, el sobreescriu
            System.out.println("ESCRIVINT ...");
            for(int i=0; i<usersList.getNumElems(); i++){
                fileUser.writeObject(usersList.getUser(i));
            }   
        } catch (IOException e){
            System.out.println("Error en l'arxiu de sortida");
        }
        
        // Mostrem el menu
        mostraMenu();

        
        // ------ Fitxer .txt ------
        try{
            BufferedWriter file = new BufferedWriter(new FileWriter("data.txt"));
            String frase= "OneDay;nameActivity;dd-mm-yyyy;dd-mm-yyyy;collective;29;city;dd-mm-yyyy;hh:mm;hh:mm;12.3";
            file.write(frase);
            file.newLine();
            frase="Periodic;nameActivity;dd-mm-yyyy;dd-mm-yyyy;collective;29;dd-mm-yyyy;hh:mm;dd-mm-yyyy;21;14.5;centerName;cityName";
            file.write(frase);
            file.newLine();
            frase = "Online;nameActivity;dd-mm-yyyy;dd-mm-yyyy;collective;29;dd-mm-yyyy;dd-mm-yyyy;linkCurso";
            file.write(frase);
            file.newLine();
            // Es fa tres cops perquè hi ha 3 línies que contenen  la informació de les classes d'activitats.
            for(int i=0; i<activities.getNumElems(); i++){
                frase = "" + activities.getActivity(i);
                file.write(frase);
                file.newLine();
            }
            file.close();
        } catch (FileNotFoundException e){
            System.out.println("Fitxer no existent");
		} catch(IOException e) {
			System.out.println("S'ha produit un error en els arxius");
        }
    }

    public static void mostraMenu(){
        System.out.println("\n\nOPCIONS DEL MENU: ");
        
    }


    
}
