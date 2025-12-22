package aplicació;

import java.time.LocalDate;
import java.time.LocalTime;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
    static Scanner teclat = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // Inicialitzem les variables per la llista d'activitats
        ActivityList activities = new ActivityList(300);
        // Mirem si existeix el fitxer, si es aixi guardem la informació dels fitxers a les variables corresponents, si no estaran buides
        try {
            BufferedReader fileRead=new BufferedReader(new FileReader("data.txt"));
            String frase="";
            frase=fileRead.readLine();
            // Ens saltem la primera frase i anem a la següent
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
        int opcio = Integer.parseInt(teclat.nextLine());
        while (opcio != 22){ //Mostrarem el menu fins que l'usuari vulgui sortir de l'aplicació
            switch (opcio){
                case 1:
                    break;
                case 2:
                    String tipusOp2 = teclat.nextLine();
                    //*Supongo que habrá que hacer lo mismo que con el menu grande pero en pequeño */
                    if(tipusOp2.equalsIgnoreCase("usuaris")){
                        opcio2("usuaris");
                    } else if(tipusOp2.equalsIgnoreCase("activitats")){
                        opcio2("activitats");
                    }
                    break;
                case 3:
                    // Llamamos la funcion de pillar la informacion de las actividades con inscripciones abiertas
                    opcio3(activities);
                    
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("Indica el nom de l'activitat");
                    String activityName7 = teclat.nextLine();
                    opcio7(activities, activityName7);
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    System.out.println("Indica el nom de l'activitat");
                    String activityName11 = teclat.nextLine();
                    opcio11(activities, activityName11);
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    opcio15(activities);
                    break;
                case 16:
                    break;
                case 17:
                    break;
                case 18:
                    break;
                case 19:
                    break;
                case 20:
                    break;
                case 21:
                    break;
            }
            mostraMenu();
            opcio = Integer.parseInt(teclat.nextLine());
        }

        
        // ------ Fitxer .txt ------
        try{
            BufferedWriter file = new BufferedWriter(new FileWriter("data.txt"));
            String frase= "Activities: activityType;activityName;startInscriptionDate;finishInscriptionDate;collectives;maxInscriptions. OneDatActivity: city;day;startTime;finishTime;price. PeriodicActivity: day;startTime;finishTime;inicialDay;weeksOfActivity;price;centerName;cityName. Online: startDayActivity;finishDayActivity;linkCourse.";
            file.write(frase);
            for(int i=0; i<activities.getNumElems(); i++){
                file.newLine();
                frase = "" + activities.getActivity(i);
                file.write(frase);
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
        System.out.println("1- Informació sobre la data actual"); //Hem de posar en diferents dates per acceptar inscripcions o no. Indicar data actual per poder-la modificar o fer operacions.
        System.out.println("2- Informació de les dades d'una llista");  //Demanem la llista desitjada. Si són de diferents tipus, es demanarà el tipus i si es vol mostrar tots els tipus o només aquest.
        System.out.println("3- Informació de les activitats en període d'inscripció"); //Indicar si encara hi han places disponibles.
        System.out.println("4- Informació d'activitats en la data actual"); //Data del punt 1. Tota la informació de cada activitat; places omplertes? Hi ha llista d'espera?
        System.out.println("5- Activitats actives en la data actual"); //Nom de les activitats. No cal classe avui, però la data actual ha d'estar entre data inicial i final.
        System.out.println("6- Activitats amb places disponibles"); //Nom de les activitats. Tant si estan encara en termini d'inscripció o no.
        System.out.println("7- Informació d'una activitat"); //Informació detallada d'una activitat a partir del seu nom.
        System.out.println("8- Informació d'un usuari"); //Informació detallada d'un usuari a partir del seu nom.
        System.out.println("9- Activitats on estàs inscrit"); //Activitats a les que està apuntat un usuari.
        System.out.println("10- Inscripció a una activitat"); //Inscripció disponible si es dona dins del termini i l'activitat s'ofereix pels col·lectius al que pertany l'usuari demandant.
        //(Continua 10) Si l'usuari està a la llista, agafar alies. Si no, demanar informació per afegir-lo. Control de places disponibles o anirà a llista d'espera. Si llista d'espera plena, cap inscripcio més.
        System.out.println("11- Usuaris inscrits en activitats i usuaris en llista d'espera"); //Mostrarem (nom o info completa?) dels usuaris inscrits en alguna activitat i els que estan en llista d'espera.
        System.out.println("12- Eliminació d'usuari d'una activitat"); //Hi ha llista d'espera --> el primer passa a formar part dels usuaris que poden accedir a l'activitat.
        System.out.println("13- Afegir una nova activitat d'un dia");
        System.out.println("14- Afegir una nova activitat periòdica");
        System.out.println("15- Afegir una nova activitat en línia");
        System.out.println("16- Valoració d'una activitat"); //Per poder valorar l'activitat: 1-l'activitat ha d'haver acabat; 2-l'usuari ha d'haver assistit a l'activitat.
        System.out.println("17- Resum de valoracions de les activitats");
        System.out.println("18- Resum de valoracions d'un usuari"); //Valoracions que ha fet un usuari.
        System.out.println("19- Mitjanes de valoracions dels col·lectius"); //Volem comparar si els usuaris dels diferents col·lectius valoren igual.
        System.out.println("20- Usuari més actiu d'un col·lectiu"); //S'haurà d'indicar el col·lectiu. Més actiu = s'ha apuntat a més activitats. Empat? S'escull qualsevol usuari que compleix els requisits.
        System.out.println("21- Baixa d'activitats"); //Donar de baixa les activitats que ja han acabat el període d'inscripció i no han omplert el 10% de les places. En activitats en línia, si el inscrits < 20 persones.
        System.out.println("22- Sortir de l'aplicació");
    }


    public static void opcio1(){
        //1. Mostrar informació sobre la data actual: pels jocs de proves farem diferents dates (per poder acceptar inscripcions o no).
        // Es mostrarà la data per poder modificar-la i fer o no operacions.
    }
    
    public static void opcio2(String tipus){
        //2. Mostrar dades d'una llista: demanem de quina es vol mostrar (usuaris o activitats).
        
    }

    public static void opcio3(ActivityList activities){
        //3. Mostrar informació activitats en període d'inscripció: places disponibles o en llista d'espera.
        LocalDate today = LocalDate.now();
        ActivityList openInscriptionActivities = activities.activitiesInscriptionOpen(today);
        for (int i = 0; i < openInscriptionActivities.getNumElems(); i++) {
            int numPlazasDisp = openInscriptionActivities.getActivity(i).getInscriptions().length - openInscriptionActivities.getActivity(i).getNumInscriptions();
            System.out.println((i+1) + "- " + openInscriptionActivities.getActivity(i).getActivityName() + ", te " + numPlazasDisp + " places disponibles");
        }

    }

    public static void opcio4(){
        //4. Mostrar informació d'activitats en data actual: tota la informació (places, llista d'espera, etc).
    }

    public static void opcio5(){
        //5. Mostrar activitats actives en la data actual: nom de les activitats; no cal classe, però la data actual entre la inicial i la final.
    }

    public static void opcio6(){
        //6. Mostrar activitats amb places disponibles: nom d'aquestes. Tant si estan en termini d'inscripció o no.
    }

    public static void opcio7(ActivityList activities, String nameActivity){
        //7. Mostrar informació d'una activitat: informació detallada a partir del nom d'aquesta.
        System.out.println(activities.getActivity(nameActivity));
    }

    public static void opcio8(){
        //8. Mostrar informació d'usuari: informació detallada a partir del nom d'aquest.
    }

    public static void opcio9(){
        //9. Mostrar activitats on estàs inscrit: totes a les que l'usuari s'ha apuntat.
    }

    public static void opcio10(){
        //10. Inscripció a una activitat: disponible si es dona dins el termini i si aquesta es s'ofereix al col·lectiu que pertanyem.
        //L'usuari pot estar a la llista (usar alies) o no, en aquest cas s'haurà de demanar la resta d'informació.
        //Control de places disponibles o llista d'espera. Si la llista d'espera està plena, prohibit cap tipus d'inscripció.
    }

    public static void opcio11(ActivityList activities, String activityName){
        //11. Mostrar els usuaris inscrits en activitats i els usuaris en llista d'espera: nom d'aquests.
        Activities activity = activities.getActivity(activityName);
        System.out.println("Usuaris Inscrits a l'activitat");
        System.out.println((activity.getInscriptionsString()));
        System.out.println("Usuaris a la llista d'espera de l'activitat");
        activity.printWaitingList();
    }

    public static void opcio12(){
        //12. Eliminicació d'usuari d'una activitat:
        //Quan s'elimini un usuari que podia accedir a l'activitat, el primer de la llista d'espera passarà a ocupar el seu lloc.
    }

    public static void opcio13(){
        //13. Afegir una nova activitat d'un dia.
    }

    public static void opcio14(){
        //14. Afegir una nova activitat periòdica.
    }

    public static void opcio15(ActivityList activities){
        //15. Afegir una nova activitat online.
        // Demanem al usuari que ens digui la informació que vol afegir a l'activitat
        System.out.print("Nom de l'activitat: ");
        String activityName = teclat.nextLine();
        

        // Colectius 
        String collectives[] = new String[3];
        System.out.println("Quins colectius vols afegir? (fica d'un en un i -1 per acabar");
        int contador = 0;
        String collective = "";
        while (contador < 3 && !collective.contains("-1")) {
            collective = teclat.nextLine();
            if (!collective.contains("-1")) {
                collectives[contador] = collective;
                contador++;
            }
        }

        System.out.print("Indica la data d'inici d'inscripcions (aaaa mm dd): ");
        int any = teclat.nextInt();
        int mes = teclat.nextInt();
        int dia = teclat.nextInt();
        LocalDate startDateInsc = LocalDate.of(any, mes, dia);

        System.out.print("Indica la data de fi d'inscripcions (aaaa mm dd): ");
        any = teclat.nextInt();
        mes = teclat.nextInt();
        dia = teclat.nextInt();
        LocalDate finishDateInsc = LocalDate.of(any, mes, dia);

        System.out.print("Indica la data de d'inici de l'activitat (aaaa mm dd): ");
        any = teclat.nextInt();
        mes = teclat.nextInt();
        dia = teclat.nextInt();
        LocalDate startDateActivity = LocalDate.of(any, mes, dia);

        System.out.print("Indica la data de fi de l'activitat (aaaa mm dd): ");
        any = teclat.nextInt();
        mes = teclat.nextInt();
        dia = teclat.nextInt();
        LocalDate finishDateActivity = LocalDate.of(any, mes, dia);
        teclat.nextLine();

        System.out.print("Indica el link del curs: ");
        String linkCourse = teclat.nextLine();



        Activities onlineActivity = new OnlineActivity("Online", activityName, collectives, startDateInsc, finishDateInsc, startDateActivity, finishDateActivity, linkCourse);
        activities.addActivity(onlineActivity);
    }

    public static void opcio16(){
        //16. Valoració d'una activitat: 
        //requisits per a que l'usuari la pugui valorar: 
            //1- l'activitat ha d'haver acabat.
            //2- l'usuari ha d'haver assistit a l'activitat.
    }

    public static void opcio17(){
        //17. Mostrar resum de valoracions de les activitats: han d'estar acabades.
    }

    public static void opcio18(){
        //18. Mostrar resum de valoracions d'un usuari: total de valoracions fetes per l'usuari indicat.
    }

    public static void opcio19(){
        //19. Mostrar mitjanes de valoracions dels col·lectius:
        // Objectiu -> comparar si els usuaris dels diferents col·lectius valoren igual o no.
    }

    public static void opcio20(){
        //20. Mostrar l'usuari més actiu del col·lectiu indicat: l'usuari més actiu serà el que s'ha apuntat a més activitats.
        // En cas d'empat entre usuaris, s'escollirà a qualsevol usuari que cpmpleixi els requisits.
    }

    public static void opcio21(){
        //21. Baixa d'activitats: donar de baixa les activitats que ja han acabat el període d'inscripció sense omplir el 10% de les places.
        // En activitats en línia es donarà si el número d'inscrits es inferior a 20 persones.
    }
}
