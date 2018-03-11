package vvfriva.manager;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import vvfriva.entity.Province;
import vvfriva.utils.HibernateUtils;

public class ProvinceManager {
	
	@SuppressWarnings("unchecked")
	public List<Province> list() {
		Session session = null;
		Transaction tx = null;
		List<Province> data = null;
		try {
			
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
			tx = session.beginTransaction();
			
			Criteria query = session.createCriteria(Province.class);
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
}
