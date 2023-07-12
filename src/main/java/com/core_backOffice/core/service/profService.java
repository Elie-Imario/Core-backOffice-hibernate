package com.core_backOffice.core.service;


import com.core_backOffice.core.beans.Professeur;
import com.core_backOffice.core.repository.profRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class profService {
    private final profRepositoryImpl profRepository;

    public profService() {
        this.profRepository = new profRepositoryImpl();
    }

    public void createProf(String firstname, String lastname, String grade){
        if (profRepository.ifExist(firstname, lastname)){
            System.out.println("Un Professeur ayant les mêmes informations existe déjà");
        }else{
            Professeur professeur = new Professeur(firstname, lastname, grade);
            profRepository.create(professeur);
        }
    }

    public List<Professeur> getlistProf(){
        List<Professeur> Professeurs;
        Professeurs = profRepository.list();

        return Professeurs;
    }

    public List<Professeur> getproflastinsert(){
        List<Professeur> Professeur;
        Professeur = profRepository.getLASTPROFINSERT();

        return Professeur;
    }

    public void deleteProf(Long id){
        Professeur professeurtoDelete = profRepository.getbyId(id);
        profRepository.delete(professeurtoDelete);
    }

    public void updateProf(Long id, String firstName, String lastname, String grade){
        Professeur professeur_to_edit = profRepository.getbyId(id);
        profRepository.update(professeur_to_edit, firstName, lastname, grade);
    }

    public List<Professeur> findProf(String searchParam){
        List<Professeur> ListProfesseur;
        ListProfesseur = profRepository.find(searchParam);

        return ListProfesseur;
    }

    public List<Professeur> getProf(Long Id){
        List<Professeur> professeur = new ArrayList<>();
        Professeur prof = profRepository.getbyId(Id);

        professeur = profRepository.convertIntoProfList(professeur, prof);

        return professeur;
    }


    public Long getTotalProf(){
        return profRepository.TotalProf();
    }
}
