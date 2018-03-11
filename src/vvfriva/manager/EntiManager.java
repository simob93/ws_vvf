package vvfriva.manager;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import vvfriva.entity.Ente;
import vvfriva.utils.CustomException;
import vvfriva.utils.HibernateUtils;

public class EntiManager extends StdManager<Ente> {

	@SuppressWarnings("unchecked")
	public List<Ente> list() {
		Session session = null;
		Transaction tx = null;
		List<Ente> data = null;
		try {
			
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
			tx = session.beginTransaction();
			
			Criteria query = session.createCriteria(Ente.class);
			query.addOrder(Order.asc("descrizione"));
			
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
	protected Class<Ente> getEntityClass() {
		return Ente.class;
	}

	@Override
	protected void operationAfterInsert(Ente object, Session session) throws CustomException {
	
	}

	@Override
	protected boolean checkCampiObbligatori(Ente object) throws CustomException {
		return true;
	}
}
