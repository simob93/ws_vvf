package vvfriva.manager;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import vvfriva.entity.Comune;
import vvfriva.utils.HibernateUtils;

public class ComuniManager {

	@SuppressWarnings("unchecked")
	public List<Comune> list() {
		Session session = null;
		Transaction tx = null;
		List<Comune> data = null;
		try {
			
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
			tx = session.beginTransaction();
			
			Criteria query = session.createCriteria(Comune.class);
			
			
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

}
