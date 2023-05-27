package com.core_backOffice.core.service;

import com.core_backOffice.core.beans.Occuper;
import com.core_backOffice.core.beans.Professeur;
import com.core_backOffice.core.repository.occRepositoryImpl;
import com.core_backOffice.core.repository.profRepositoryImpl;
import com.core_backOffice.core.repository.salleRepositoryImpl;

import java.util.Date;
import java.util.List;

public class occService {
    private final occRepositoryImpl occRepository;
    private final salleRepositoryImpl salleRepository;
    private final profRepositoryImpl profRepository;

    public occService() {
        this.occRepository = new occRepositoryImpl();
        this.salleRepository = new salleRepositoryImpl();
        this.profRepository = new profRepositoryImpl();
    }

    public void occuperUneSalle(Long idProf, Long idSalle, Date date_occ){
        Occuper occuper = new Occuper(profRepository.getbyId(idProf), salleRepository.getbyId(idSalle), date_occ);
        occRepository.create(occuper);
    }

    public List<Object[]> getlistOcc(){
        List<Object[]> Occupes;
        Occupes = (List<Object[]>) occRepository.list();

        return Occupes;
    }

    public List<Object[]> getocclastinsert(){
        List<Object[]> occ;
        occ = occRepository.getLASTOCCINSERT();

        return occ;
    }

    public List<Object[]> findOccById(Long Id){
        List<Object[]> occ;
        occ = occRepository.findbyId(Id);

        return occ;
    }

    public Occuper getOccuper(Long id){
        Occuper occuper;
        occuper = occRepository.getbyId(id);

        return occuper;
    }

    public void removeocc(Long id){
        Occuper occupertoDelete = occRepository.getbyId(id);
        occRepository.delete(occupertoDelete);
    }

    public void updateReservation(Long id, Long new_Salle_id, Date new_date){
        Occuper occupertoUpdate = occRepository.getbyId(id);
        occRepository.update(occupertoUpdate, salleRepository.getbyId(new_Salle_id), new_date);
    }

    public Date GetFormattedDate(String datetoconvert){
        Date dateconverted = occRepository.convertToDate(datetoconvert);
        return dateconverted;
    }

    public Long getTotalOcc(){
        return occRepository.TotalOcc();
    }

    public salleRepositoryImpl getSalleRepository() {
        return salleRepository;
    }

    public profRepositoryImpl getProfRepository() {
        return profRepository;
    }

}
