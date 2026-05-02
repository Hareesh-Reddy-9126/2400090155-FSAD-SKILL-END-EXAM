package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo {

    public static void main(String[] args) {

        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();

        // INSERT
        Transaction tx1 = session.beginTransaction();

        Movie m1 = new Movie("RRR", "2022", "Released", 9.0);
        session.save(m1);

        tx1.commit();
        System.out.println("Record Inserted");

        // UPDATE using HQL
        Transaction tx2 = session.beginTransaction();

        String hql = "update Movie set name=?1, status=?2 where id=?3";
        Query query = session.createQuery(hql);

        query.setParameter(1, "RRR Updated");
        query.setParameter(2, "Blockbuster");
        query.setParameter(3, 1);

        int rows = query.executeUpdate();

        tx2.commit();
        System.out.println("Rows Updated: " + rows);

        session.close();
        sf.close();
    }
}