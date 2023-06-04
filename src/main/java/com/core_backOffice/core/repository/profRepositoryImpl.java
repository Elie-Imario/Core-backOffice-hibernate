package com.core_backOffice.core.repository;

import com.core_backOffice.core.HibernateUtil;
import com.core_backOffice.core.beans.Professeur;
import com.core_backOffice.core.beans.Salle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class profRepositoryImpl {
    // CREATE Professeur
    public void create(Professeur professeur){
        Transaction tx = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            professeur.setNom(professeur.getNom());
            professeur.setPrenom(professeur.getPrenom());
            professeur.setGrade(professeur.getGrade());

            session.persist(professeur);
            tx.commit();

            System.out.println("Le professeur a été ajouté avec succès!");
        }catch (Exception e){
            if(tx!=null){
                tx.rollback();
            }
            System.out.println(e);
        }finally {
            if(session!=null){
                session.close();
            }
        }
    }

    // GET Professeur By Id
    public Professeur getbyId(Long id){
        Professeur professeur = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx =session.beginTransaction();

            professeur = session.get(Professeur.class, id);
            tx.commit();

        }catch (Exception e){
            e.printStackTrace();
        }

        return professeur;
    }

    //LIST ALL PROFESSEUR
    public List<Professeur> list(){
        Session session = null;
        Query<Professeur> query = null;
        List<Professeur> allProfesseurs = new ArrayList<>();
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx =session.beginTransaction();
            query= session.createQuery("SELECT p FROM Professeur p", Professeur.class);
            allProfesseurs = profRepositoryImpl.iterateResult(allProfesseurs, query);
        }catch (Exception e){
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        }finally {
            if(session != null) session.close();
        }
        return allProfesseurs;
    }


    public List<Professeur> getLASTPROFINSERT(){
        Session session = null;
        Query<Professeur> query = null;
        List<Professeur> professeur = new ArrayList<>();
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx =session.beginTransaction();
            query= session.createQuery("SELECT p FROM Professeur p order by p.id_prof desc ", Professeur.class);
            query.setMaxResults(1);
            professeur = iterateUniqueResult(professeur, query);
        }catch (Exception e){
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        }finally {
            if(session != null) session.close();
        }
        return professeur;
    }

    //DELETE PROF
    public void delete(Professeur professeur){
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.delete(professeur);
            tx.commit();

            System.out.println("Le professeur a été supprimé!");

        }catch (Exception e){
            if(tx!=null){
                tx.rollback();
            }
        }finally {
            if(session!=null){
                session.close();
            }
        }
    }


    // UPDATE PROFESSEUR
    public void update(Professeur prof_to_edit, String firstname, String lastname, String grade){
        Transaction tx = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            prof_to_edit.setNom(firstname);
            prof_to_edit.setPrenom(lastname);
            prof_to_edit.setGrade(grade);
            session.update(prof_to_edit);
            tx.commit();

            System.out.println("Le professeur a été modifié avec succès!");
        }catch (Exception e){
            if(tx!=null){
                tx.rollback();
            }
            System.out.println(e);
        }finally {
            if(session!=null){
                session.close();
            }
        }
    }

    // SEARH PROFESSEUR
    public List<Professeur> find(Long id, String name){
        Session session = null;
        Transaction tx = null;
        List<Professeur> resultSearch = new ArrayList<>();
        String sql;
        if((id != null) && !(name.isBlank())){
            sql = "SELECT p FROM Professeur p WHERE p.id_prof=?1 AND p.nom=?2";
        }else{
            sql = "SELECT p FROM Professeur p WHERE p.id_prof=?1 OR p.nom=?2";
        }

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query<Professeur> query = session.createQuery(sql, Professeur.class);
            query.setParameter(1, id);
            query.setParameter(2, name);
            resultSearch = profRepositoryImpl.iterateResult(resultSearch, query);
            tx.commit();
        }catch (Exception e){
            if(tx!=null){
                tx.rollback();
            }
            return null;
        }finally {
            if(session!=null){
                session.close();
            }
        }
        return resultSearch;
    }

    public List<Professeur> convertIntoProfList(List<Professeur> prof, Professeur ptoConvert){
        Professeur p = new Professeur();
        p.setId_prof(ptoConvert.getId_prof());
        p.setNom(ptoConvert.getNom());
        p.setPrenom(ptoConvert.getPrenom());
        p.setGrade(ptoConvert.getGrade());

        prof.add(p);
        return prof;
    }

    private static List<Professeur> iterateResult(List<Professeur> resultSearch, Query<Professeur> query) {
        for(Professeur profSearched : query.getResultList()){
            Professeur professeur = new Professeur();
            professeur.setId_prof(profSearched.getId_prof());
            professeur.setNom(profSearched.getNom());
            professeur.setPrenom(profSearched.getPrenom());
            professeur.setGrade(profSearched.getGrade());

            resultSearch.add(professeur);
        }
        return resultSearch;
    }


    private static List<Professeur> iterateUniqueResult(List<Professeur> resultSearch, Query<Professeur> query){
        Professeur professeurUniqueResult, prof = new Professeur();
        professeurUniqueResult = query.uniqueResult();
        prof.setId_prof(professeurUniqueResult.getId_prof());
        prof.setNom(professeurUniqueResult.getNom());
        prof.setPrenom(professeurUniqueResult.getPrenom());
        prof.setGrade(professeurUniqueResult.getGrade());

        resultSearch.add(prof);
        return resultSearch;
    }

    public boolean ifExist(String Nom, String Prenom){
        Session session = null;
        Transaction tx = null;
        Query query = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            query= session.createQuery("SELECT p FROM Professeur p WHERE p.nom=?1 AND p.prenom=?2", Professeur.class);
            query.setParameter(1, Nom);
            query.setParameter(2, Prenom);
            List<Professeur> result = query.getResultList();
            tx.commit();
            if(result.isEmpty()) return false;
            else return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            if(session!=null){
                session.close();
            }
        }
    }

    public Long TotalProf(){
        Long Total = 0L;
        Session session = null;
        Query<Salle> query = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx =session.beginTransaction();
            Total= (Long) session.createQuery("SELECT COUNT(*) FROM Professeur p").uniqueResult();

            return Total;
        }catch (Exception e){
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return 0L;
        }finally {
            if(session != null) session.close();
        }
    }
}
