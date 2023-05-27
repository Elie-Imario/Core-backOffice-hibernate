package com.core_backOffice.core;

import com.core_backOffice.core.beans.Professeur;
import com.core_backOffice.core.service.profService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI_Professeur {
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int choice;
        profService profService = new profService();

        System.out.println("---------------PROFESSEUR UI-----------------");
        System.out.println("1- Ajouter un professeur" +
                            "\n 2- Modifier un professeur" +
                            "\n 3- Rechercher un professeur" +
                            "\n 4- Supprimer un professeur" +
                            "\n 5- Lister les professeurs" +
                            "\n 6- Dernier prof ajouté" +
                            "\n 7- Total Prof");

        System.out.println("Quel est votre choix?");
        choice = sc.nextInt();

        sc.nextLine();
        if(choice == 1){
            System.out.println("--------------Ajouter un professeur-------------");
            String firstname, lastname, grade;
            System.out.println("Quel est son nom?");
            firstname = sc.nextLine();
            System.out.println("Quel est son prénom?");
            lastname = sc.nextLine();
            System.out.println("De quel grade est-il?");
            grade = sc.nextLine();
            profService.createProf(firstname, lastname, grade);

        }else if(choice == 2){
            System.out.println("--------------Modifier un professeur-------------");
            String newfirstname, newlastname, newgrade;
            Long id_to_edit;

            System.out.println("Qui est le professeur à modifier");
            id_to_edit = sc.nextLong();

            sc.nextLine();
            System.out.println("Quel est son nouveau nom?");
            newfirstname = sc.nextLine();
            System.out.println("Quel est son nouveau prénom?");
            newlastname = sc.nextLine();
            System.out.println("De quel nouveau grade est-il?");
            newgrade = sc.nextLine();
            profService.updateProf(id_to_edit, newfirstname, newlastname, newgrade);
        }else if(choice == 3){
            System.out.println("---------------SEARCH FOR Prof--------------------");
            System.out.println("Entrer l'identifiant du professeur que vous vouler rechercher:");
            Long id_serached = sc.nextLong();
            sc.nextLine();
            System.out.println("Entrer ensuite son nom:");
            String name = sc.nextLine();

            System.out.println("Voici le resulat de votre recherche");
            List<Professeur> profs_ = new ArrayList<>();
            profs_ = profService.findProf(id_serached, name);

            for(Professeur professeur : profs_){
                System.out.println("|"+professeur.getNom()+"|"+professeur.getPrenom()+"|"+professeur.getGrade()+"|");
            }
        }else if(choice == 4){
            System.out.println("Quel professeur voulez vous retirer?");
            Long id_to_delete = sc.nextLong();
            profService.deleteProf(id_to_delete);
        }else if(choice == 5){
            System.out.println("Voici la liste des professeurs");
            List<Professeur> profs = new ArrayList<>();

            profs = profService.getlistProf();
            for(Professeur professeur : profs){
                System.out.println(professeur.getId_prof()+"|"+professeur.getNom()+"|"+professeur.getPrenom()+"|"+professeur.getGrade()+"|");
            }
        }else if(choice == 6){
            System.out.println("Voici le professeur ajouté en dernier");
            List<Professeur> prof = new ArrayList<>();

            prof = profService.getproflastinsert();
            for(Professeur professeur : prof){
                System.out.println(professeur.getId_prof()+"|"+professeur.getNom()+"|"+professeur.getPrenom()+"|"+professeur.getGrade()+"|");
            }
        }else if(choice == 7){
            System.out.println("Le total des Prof:"+profService.getTotalProf());
        }else{
            System.out.println("Mauvais choix");
        }

    }
}
