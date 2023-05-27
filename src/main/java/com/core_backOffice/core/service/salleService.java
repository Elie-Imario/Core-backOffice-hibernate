package com.core_backOffice.core.service;


import com.core_backOffice.core.beans.Salle;
import com.core_backOffice.core.repository.salleRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class salleService {
    private final salleRepositoryImpl salleRepository;

    public salleService() {
        this.salleRepository = new salleRepositoryImpl();
    }

    public void createSalle(String designation){
        if (salleRepository.ifExist(designation)){
            System.out.println("Une salle ayant la même designation existe déjà");
        }else{
            Salle salle = new Salle(designation);
            salleRepository.create(salle);
        }
    }

    public List<Salle> getlastsalle(){
        List<Salle> Salle;
        Salle = salleRepository.getLASTSALLEINSERT();

        return Salle;
    }


    public List<Salle> getlistSalle(){
        List<Salle> Salles;
        Salles = salleRepository.list();

        return Salles;
    }

    public void updateSalle(Long id_to_edit, String designation){
        System.out.println("ID: "+ id_to_edit);
        Salle salletoUpdate = salleRepository.getbyId(id_to_edit);
        salleRepository.update(salletoUpdate, designation);
    }


    public void deleteSalle(Long id){
        Salle salletoDelete = salleRepository.getbyId(id);
        salleRepository.delete(salletoDelete);
    }

    public List<Salle> getSalle(Long Id){
        List<Salle> Salle = new ArrayList<>();
        Salle s = salleRepository.getbyId(Id);

        Salle = salleRepository.convertIntoSalleList(Salle, s);

        return Salle;
    }

    public Long gettotalSalle(){
        return salleRepository.TotalSalle();
    }

}
