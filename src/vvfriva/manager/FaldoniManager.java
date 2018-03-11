package vvfriva.manager;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import vvfriva.entity.Faldone;
import vvfriva.utils.CustomException;
import vvfriva.utils.HibernateUtils;

public class FaldoniManager  extends StdManager<Faldone> {

	@SuppressWarnings("unchecked")
	public List<Faldone> list() {
		Session session = null;
		Transaction tx = null;
		List<Faldone> data = null;
		try {
			
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
			tx = session.beginTransaction();
			
			Criteria query = session.createCriteria(Faldone.class);
			query.addOrder(Order.asc("numero"));
			
			data = query.list();
			tx.commit();
			
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
	protected Class<Faldone> getEntityClass() {
		// TODO Auto-generated method stub
		return Faldone.class;
	}

	@Override
	protected void operationAfterInsert(Faldone object, Session session) throws CustomException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean checkCampiObbligatori(Faldone object) throws CustomException {
		// TODO Auto-generated method stub
		return true;
	}
}
