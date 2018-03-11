package vvfriva.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import vvfriva.entity.Comune;
import vvfriva.entity.Coordinate;
import vvfriva.entity.Ente;
import vvfriva.entity.Faldone;
import vvfriva.entity.Password;
import vvfriva.entity.Protocolli;
import vvfriva.entity.Province;
import vvfriva.entity.Rubrica;
import vvfriva.entity.Squadre;
import vvfriva.entity.Strade;
import vvfriva.entity.Vigili;

public class HibernateUtils{
 
    private static SessionFactory sessionFactory;
     
    private static SessionFactory sessionAnnotationFactory;
 
    private static SessionFactory buildSessionFactory() {
        try {
        	
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");
             
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");
             
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
             
            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    private static SessionFactory buildSessionAnnotationFactory() {
        try {
        	
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addPackage("vvfriva.entity"); //the fully qualified package name
            configuration.addAnnotatedClass(Vigili.class);
            configuration.addAnnotatedClass(Squadre.class);
            configuration.addAnnotatedClass(Ente.class);
            configuration.addAnnotatedClass(Faldone.class);
            configuration.addAnnotatedClass(Protocolli.class);
            configuration.addAnnotatedClass(Comune.class);
            configuration.addAnnotatedClass(Province.class);
            configuration.addAnnotatedClass(Rubrica.class);
            configuration.addAnnotatedClass(Strade.class);
            configuration.addAnnotatedClass(Password.class);
            configuration.addAnnotatedClass(Coordinate.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            
            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
   
     
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
     
    public static SessionFactory getSessionAnnotationFactory() {
    	
        if(sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }

}