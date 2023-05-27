package com.core_backOffice.core;

import com.core_backOffice.core.beans.Salle;
import com.core_backOffice.core.service.salleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI_Salle {
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int choice;
        salleService salleservice = new salleService();

        System.out.println("---------------SALLE UI-----------------");
        System.out.println("1- Ajouter une salle" +
                            "\n 2- Modifier une salle" +
                            "\n 3- Supprimer une salle" +
                            "\n 4- Lister les salles" +
                            "\n 5- Total salle");

        System.out.println("Quel est votre choix?");
        choice = sc.nextInt();

        sc.nextLine();
        if(choice == 1){
            System.out.println("--------------Ajouter une salle-------------");
            String designation;
            System.out.println("Renseigner la designation de la salle");
            designation = sc.nextLine();
            salleservice.createSalle(designation);

        }else if(choice == 2){
            System.out.println("--------------Modifier une salle-------------");
            String newDesignation;
            Long id_to_edit;

            System.out.println("Quelle est la salle à modifier");
            id_to_edit = sc.nextLong();

            sc.nextLine();
            System.out.println("Quelle est la nouvelle designation de la salle?");
            newDesignation = sc.nextLine();
            salleservice.updateSalle(id_to_edit, newDesignation);
        }else if(choice == 3){
            System.out.println("Quelle salle voulez vous retirer?");
            Long id_to_delete = sc.nextLong();
            salleservice.deleteSalle(id_to_delete);
        }else if(choice == 4){
            System.out.println("Voici la liste des salles");
            List<Salle> salles = new ArrayList<>();

            salles = salleservice.getlistSalle();
            for(Salle salle : salles){
                System.out.println(salle.getId_Salle()+"|"+salle.getDesignation()+"|");
            }
        }else if(choice == 5){
            System.out.println("Le total des salles:"+salleservice.gettotalSalle());
        } else{
            System.out.println("Mauvais choix");
        }

    }
}
