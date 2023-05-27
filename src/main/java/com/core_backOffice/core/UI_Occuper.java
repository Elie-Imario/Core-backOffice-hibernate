package com.core_backOffice.core;

import com.core_backOffice.core.beans.Occuper;
import com.core_backOffice.core.beans.Professeur;
import com.core_backOffice.core.service.occService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UI_Occuper {
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int choice;
        occService occService = new occService();

        System.out.println("---------------OCCUPER UI-----------------");
        System.out.println("1- Reserver une salle" +
                            "\n2- Modifier une reservation" +
                            "\n3- Supprimer une reservation" +
                            "\n4- Lister les salles occupées" +
                            "\n5- La derniere reservation" +
                            "\n6- Detail d'une reservation" +
                            "\n7- Total OCC");

        System.out.println("Quel est votre choix?");
        choice = sc.nextInt();

        sc.nextLine();
        if(choice == 1){
            System.out.println("--------------Reserver une salle-------------");
            Long idProf, idSalle;
            Date date_occ = null;
            System.out.println("Entrez le code du Prof");
            idProf = sc.nextLong();
            System.out.println("Quel est le code du salle?");
            idSalle = sc.nextLong();
            sc.nextLine();
            System.out.println("Quand serait cette reservation?");
            String dateToFormat = sc.nextLine();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                date_occ = dateFormat.parse(dateToFormat);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            occService.occuperUneSalle(idProf, idSalle, date_occ);

        }else if(choice == 2){
            System.out.println("--------------Modifier une reservation-------------");
            Long id_to_edit, id_new_Salle;
            Date new_date_reservation = null;

            System.out.println("Quelle est la reservation à modifier");
            id_to_edit = sc.nextLong();

            sc.nextLine();
            System.out.println("La reservation sera assignée à quelle salle?");
            id_new_Salle = sc.nextLong();
            sc.nextLine();
            System.out.println("Quand serait cette reservation?");
            String dateToFormat = sc.nextLine();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                new_date_reservation = dateFormat.parse(dateToFormat);
            } catch (ParseException e) {
                e.printStackTrace();
                new_date_reservation = null;
            }

            occService.updateReservation(id_to_edit, id_new_Salle, new_date_reservation);

        }else if(choice == 3){
            System.out.println("---------------Supprimer une reservation--------------------");
            System.out.println("Quelle reservation voulez vous annuler?");
            Long id_to_delete = sc.nextLong();
            occService.removeocc(id_to_delete);

        }else if(choice == 4){
            System.out.println("-----------------Lister les salles occupées----------------------");
            System.out.println("Voici la liste des reservations effectuées");
            List<Object[]> occs ;

            Long id;
            String firstName, lastName, designation;
            Date date_occ;
            occs = (List<Object[]>) occService.getlistOcc();
            for(Object[] reservation : occs){
                id = (Long) reservation [0];
                firstName = (String) reservation[1];
                lastName = (String) reservation [2];
                designation = (String) reservation [3];
                date_occ = (Date) reservation [4];
                System.out.println(id+"|"+firstName+"|"+lastName+"|"+designation+"|"+date_occ+"|");
            }
        }else if(choice == 5){
            System.out.println("Voici la reservation ajoutée en dernier");
            List<Object[]> occ;

            Long id;
            String firstName, lastName, designation;
            Date date_occ;
            occ = (List<Object[]>) occService.getocclastinsert();
            for(Object[] reservation : occ){
                id = (Long) reservation [0];
                firstName = (String) reservation[1];
                lastName = (String) reservation [2];
                designation = (String) reservation [3];
                date_occ = (Date) reservation [4];
                System.out.println(id+"|"+firstName+"|"+lastName+"|"+designation+"|"+date_occ+"|");
            }
        }else if(choice == 6){
            System.out.println("---------------Détail d' une reservation--------------------");
            System.out.println("Quelle reservation voulez vous voir?");
            Long id_to_delete = sc.nextLong();
            Occuper occ = occService.getOccuper(id_to_delete);

            System.out.println(occ.getId()+"|"+occ.getProf().getNom()+"|"+occ.getProf().getPrenom()+"|"+occ.getSalle().getDesignation()+"|"+occ.getDate_occupation()+"|");

        }else if(choice == 7){
            System.out.println("Le total des reservations:"+occService.getTotalOcc());
        }else{
            System.out.println("Mauvais choix");
        }

    }
}
