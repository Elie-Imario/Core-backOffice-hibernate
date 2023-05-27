package com.core_backOffice.core.repository;


import com.core_backOffice.core.HibernateUtil;
import com.core_backOffice.core.beans.Occuper;
import com.core_backOffice.core.beans.Professeur;
import com.core_backOffice.core.beans.Salle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class occRepositoryImpl {
    public void create(Occuper occuper){
        Transaction tx = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            occuper.setProf(occuper.getProf());
            occuper.setSalle(occuper.getSalle());
            occuper.setDate_occupation(occuper.getDate_occupation());
            session.persist(occuper);
            tx.commit();

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
    //GET RESERVATION By Id
    public Occuper getbyId(Long id){
        Occuper occ = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx =session.beginTransaction();

            occ = session.get(Occuper.class, id);
            tx.commit();

        }catch (Exception e){
            e.printStackTrace();
        }

        return occ;
    }

    public List<Object[]> getLASTOCCINSERT(){
        Session session = null;
        TypedQuery query = null;
        List<Object[]> occ = new ArrayList<>();
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx =session.beginTransaction();
            query= session.createQuery("select o.id, o.prof.nom, o.prof.prenom, o.salle.designation, o.date_occupation FROM Occuper o order by o.id desc ", Object[].class);
            query.setMaxResults(1);
            occ = (List<Object[]>)iterateUniqueResult(occ, query);
        }catch (Exception e){
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        }finally {
            if(session != null) session.close();
        }
        return occ;
    }


    public List<Object[]> findbyId(Long id){
        Session session = null;
        TypedQuery query = null;
        List<Object[]> occ = new ArrayList<>();
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx =session.beginTransaction();
            query= session.createQuery("select o.id, o.prof.id_prof, o.prof.nom, o.prof.prenom, o.salle.id_Salle, o.salle.designation, o.date_occupation FROM Occuper o WHERE o.id =?1 ", Object[].class);
            query.setParameter(1, id);
            occ = (List<Object[]>)iterateUniqueResult(occ, query);
        }catch (Exception e){
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        }finally {
            if(session != null) session.close();
        }
        return occ;
    }

    //DELETE RESERVATION
    public void delete(Occuper occuper){
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.delete(occuper);
            tx.commit();


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

    // UPDATE RESERVATION
    public void update(Occuper occ_to_edit, Salle new_Salle, Date new_Date){
        Transaction tx = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            occ_to_edit.setSalle(new_Salle);
            occ_to_edit.setDate_occupation(new_Date);
            session.update(occ_to_edit);
            tx.commit();

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

    //LIST ALL RESERVATION
    public List<Object[]> list(){
        Session session = null;
        TypedQuery query = null;
        List<Object[]> allreservations;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx =session.beginTransaction();
            query= session.createQuery("select o.id, o.prof.nom, o.prof.prenom, o.salle.designation, o.date_occupation FROM Occuper o", Object[].class);
            allreservations = (List<Object[]>)query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        }finally {
            if(session != null) session.close();
        }
        return allreservations;
    }

    private static List<Object[]> iterateUniqueResult(List<Object[]> resultSearch, TypedQuery query){
        List<Object[]> professeurUniqueResult = (List<Object[]>)query.getResultList();
        for(Object[] reservation : professeurUniqueResult){
            resultSearch.add(reservation);
        }
        return resultSearch;
    }

    public Date convertToDate(String dateToConvert){
        Date date;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = dateFormat.parse(dateToConvert);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }

        return date;
    }

    public Long TotalOcc(){
        Long Total = 0L;
        Session session = null;
        Query<Salle> query = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx =session.beginTransaction();
            Total= (Long) session.createQuery("SELECT COUNT(*) FROM Occuper o").uniqueResult();

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
