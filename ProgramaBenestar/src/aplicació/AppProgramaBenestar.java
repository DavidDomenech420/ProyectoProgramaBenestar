package aplicació;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class AppProgramaBenestar {
    public static void main(String[] args) throws Exception {
        // Mirem si existeix el fitxer, si es aixi guardem la informació dels fitxers a les variables corresponents, si no estaran buides
        try {
            BufferedReader fileRead=new BufferedReader(new FileReader("data.txt"));
            String frase="";
            frase=fileRead.readLine();
            while (frase != null) {
                
            }
            fileRead.close();
        } catch (FileNotFoundException e) {
            System.out.println("No hi ha cap fitxer creat actualment");
        }
        

        // Mostrem el menu
    }

    public static void mostraMenu(){
        System.out.println("\n\nOPCIONS DEL MENU: ");
    }

}
