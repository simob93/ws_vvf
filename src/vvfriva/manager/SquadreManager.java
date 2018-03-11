package vvfriva.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import vvfriva.entity.Squadre;
import vvfriva.model.KeyValue;
import vvfriva.utils.HibernateUtils;

public class SquadreManager {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<KeyValue> list() {
		Session session = null;
		Transaction tx = null;
		List<Squadre> listSquadre = null;
		List<KeyValue> data = new ArrayList<>();
		try {
			
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
			tx = session.beginTransaction();
			
			Criteria query = session.createCriteria(Squadre.class);
			listSquadre = query.list();
			
			if (listSquadre.size() > 0) {
				for(Squadre squadra: listSquadre) {
					data.add(new KeyValue<>(squadra.getId(), squadra.getNumeroSquadra().toString(), null));
				}
			}
			
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
