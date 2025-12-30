package aplicació;

import java.time.LocalDate;

import dades.Activities;
import dades.Inscriptions;
import list.ActivityList;
import list.InscriptionList;

public class funcionsDavid {
    private static LocalDate usedDate = LocalDate.now();

    // ------ 19º OPCIÓ DEL MENU ------
    public static void option19(ActivityList activities){
        //19. Mostrar mitjanes de valoracions dels col·lectius:
        // Objectiu -> comparar si els usuaris dels diferents col·lectius valoren igual o no.

        // Pillem Totes les activitats que ja s'han acabat
        ActivityList finishActivities = activities.activitiesFinished(usedDate);
        // Recorrem totes les activitats i hem de mirar les inscripcions de cada activitat, despres recorrem les inscripcions i segons el colectiu sumem una variable de mitjana i altre i el contador
        for (int i = 0; i < finishActivities.getNumElems(); i++) {
            float mitjanaStudent = 0;
            float mitjanaPDI = 0;
            float mitjanaPTGAS = 0;
            int contadorStudent = 0;
            int contadorPDI = 0;
            int contadorPTGAS = 0;
            Activities activity = finishActivities.getActivity(i);
            InscriptionList inscriptions = activity.getInscriptions();
            for (int j = 0; j < inscriptions.getNumElems(); j++) {
                Inscriptions inscription = inscriptions.getInscription(j);
                // Pillem el colectiu
                if (inscription.getUser().getUserType().equalsIgnoreCase("Student")) {
                    mitjanaStudent += inscription.getAssessment();
                    contadorStudent++;
                }
                else if (inscription.getUser().getUserType().equalsIgnoreCase("PDI")) {
                    mitjanaPDI += inscription.getAssessment();
                    contadorPDI++;
                }
                else if (inscription.getUser().getUserType().equalsIgnoreCase("PTGAS")) {
                    mitjanaPTGAS += inscription.getAssessment();
                    contadorPTGAS++;
                }
            }

            // Fem les mitjanes i les mostrem
            System.out.println("Activitat " + (i + 1) + " Valoracions - Student: " + (mitjanaStudent/contadorStudent) + ", PDI: " + (mitjanaPDI/contadorPDI) + ", PTGAS: " + (mitjanaPTGAS/contadorPTGAS));
        }
    }
    //--------------------------------
}
