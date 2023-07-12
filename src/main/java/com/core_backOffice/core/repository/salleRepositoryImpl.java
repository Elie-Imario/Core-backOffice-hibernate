package com.core_backOffice.core.repository;

import com.core_backOffice.core.HibernateUtil;
import com.core_backOffice.core.beans.Salle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class salleRepositoryImpl {
    // CREATE Salle
    public void create(Salle salle){
        Transaction tx = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            salle.setDesignation(salle.getDesignation());

            session.persist(salle);
            tx.commit();

            System.out.println("Salle ajouté avec succès!");
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

    // GET Salle By Id
    public Salle getbyId(Long id){
        Salle salle = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx =session.beginTransaction();

            salle = session.get(Salle.class, id);
            tx.commit();

        }catch (Exception e){
            e.printStackTrace();
        }

        return salle;
    }

    public List<Salle> getLASTSALLEINSERT(){
        Session session = null;
        Query<Salle> query = null;
        List<Salle> salle = new ArrayList<>();
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx =session.beginTransaction();
            query= session.createQuery("SELECT s FROM Salle s order by s.id_Salle desc ", Salle.class);
            query.setMaxResults(1);
            salle = iterateUniqueResult(salle, query);
        }catch (Exception e){
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        }finally {
            if(session != null) session.close();
        }
        return salle;
    }


    //LIST ALL Salles
    public List<Salle> list(){
        Session session = null;
        Query<Salle> query = null;
        List<Salle> allSalles = new ArrayList<>();
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx =session.beginTransaction();
            query= session.createQuery("SELECT s FROM Salle s order by s.id_Salle desc", Salle.class);
            allSalles = salleRepositoryImpl.iterateResult(allSalles, query);
        }catch (Exception e){
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        }finally {
            if(session != null) session.close();
        }
        return allSalles;
    }

    //DELETE Salle
    public void delete(Salle salle){
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.delete(salle);
            tx.commit();

            System.out.println("Salle a rétirée de la base de donnée!");

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

    public List<Salle> convertIntoSalleList(List<Salle> salle, Salle salletoConvert){
        Salle s = new Salle();
        s.setId_Salle(salletoConvert.getId_Salle());
        s.setDesignation(salletoConvert.getDesignation());

        salle.add(s);
        return salle;
    }

    // UPDATE Salle
    public void update(Salle salle_to_edit, String designation){
        Transaction tx = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            salle_to_edit.setDesignation(designation);
            session.update(salle_to_edit);
            tx.commit();

            System.out.println("Salle modifiée avec succès!");
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

    private static List<Salle> iterateUniqueResult(List<Salle> resultSearch, Query<Salle> query){
        Salle salleUniqueResult, salle = new Salle();
        salleUniqueResult = query.uniqueResult();
        salle.setId_Salle(salleUniqueResult.getId_Salle());
        salle.setDesignation(salleUniqueResult.getDesignation());

        resultSearch.add(salle);
        return resultSearch;
    }


    private static List<Salle> iterateResult(List<Salle> resultSearch, Query<Salle> query) {
        for(Salle salleSearched : query.getResultList()){
            Salle salle = new Salle();
            salle.setId_Salle(salleSearched.getId_Salle());
            salle.setDesignation(salleSearched.getDesignation());

            resultSearch.add(salle);
        }
        return resultSearch;
    }

    public boolean ifExist(String designation){
        Session session = null;
        Transaction tx = null;
        Query query = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            query= session.createQuery("SELECT s FROM Salle s WHERE s.designation=?1", Salle.class);
            query.setParameter(1, designation);
            List<Salle> result = query.getResultList();
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

    public Long TotalSalle(){
        Long Total = 0L;
        Session session = null;
        Query<Salle> query = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx =session.beginTransaction();
            Total= (Long) session.createQuery("SELECT COUNT(*) FROM Salle s").uniqueResult();

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
