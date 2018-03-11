package vvfriva.manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import vvfriva.entity.Coordinate;
import vvfriva.entity.Strade;
import vvfriva.utils.CustomException;
import vvfriva.utils.HibernateUtils;

public class StradeManager extends StdManager<Coordinate> {
	private Session session = null;
	private Session getSession() {
		if (session == null) {
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
		}
		return session;
	}
	
	@SuppressWarnings("unchecked")
	public List<Coordinate> list() {
		Session session = null;
		Transaction tx = null;
		List<Coordinate> data = null;
		try {
			
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
			tx = session.beginTransaction();
			
			Criteria query = session.createCriteria(Coordinate.class);
			data = query.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return data;
	}

	@Override
	protected Class<Coordinate> getEntityClass() {
		// TODO Auto-generated method stub
		return Coordinate.class;
	}

	@Override
	protected void operationAfterInsert(Coordinate object, Session session) throws CustomException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean checkCampiObbligatori(Coordinate object) throws CustomException {
		// TODO Auto-generated method stub
		return true;
	}

	public Boolean importcoordinate() throws IOException {
		String csvFile = "C:/Users/simone/Downloads/IDRANTI_ALTO_GARDA.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        Boolean success = true;
        Transaction tx = null;
        StringBuilder sb = new StringBuilder();
	    try {
	    	
	    	tx = this.getSession().beginTransaction();
            br = new BufferedReader(new FileReader(csvFile));
            int i = 1;
            while ((line = br.readLine()) != null) {
            	sb = new StringBuilder();
            	if(i != 1) {
            		
	                String[] idrante = line.split(cvsSplitBy);
	                String comune = idrante[1],
	                	   tipo = idrante[3],
	                	   attacco = idrante[4],
	                	   uscita = idrante[5],
	                	   posizione = idrante[6],
	                	   via = idrante[7],
	                	   frazione = idrante[8],
	                	   longitudine = idrante[9],
	                	   latitudine = idrante[10];
	                			
	                sb.append("INSERT INTO coordinate(LATITUDINE, LONGITUDINE, TIPO, ATTACCO, USCITA, POSIZIONE, VIA, FRAZIONE, COMUNE) VALUES (:LATITUDINE, :LONGITUDINE, :TIPO, :ATTACCO, :USCITA, :POSIZIONE, :VIA, :FRAZIONE, :COMUNE)");
	                 
	  	    	  	  this.getSession().createSQLQuery(sb.toString())
	  	    	  	  .setFloat("LONGITUDINE", Float.parseFloat(longitudine))
	  	    	  	  .setFloat("LATITUDINE", Float.parseFloat(latitudine))
	  	    	  	  .setString("COMUNE", comune)
	  	    	  	  .setString("FRAZIONE", frazione)
	  	    	  	  .setString("VIA", via)
	  	    	  	  .setString("POSIZIONE", posizione)
	  	    	  	  .setString("USCITA", uscita)
	  	    	  	  .setString("ATTACCO", attacco)
	  	    	  	  .setString("TIPO", tipo)
	  	    	  	  .executeUpdate();
            	}
            	i++;
                  
            }
            tx.commit();

        } catch (FileNotFoundException e) {
        	success = false;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            success = false;
        } finally {
            if (br != null) {
            	br.close();
            }
        }
		return success;

	}
}
