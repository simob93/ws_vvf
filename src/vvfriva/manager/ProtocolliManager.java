package vvfriva.manager;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import vvfriva.entity.Protocolli;
import vvfriva.utils.Controlli;
import vvfriva.utils.CustomException;
import vvfriva.utils.HibernateUtils;

public class ProtocolliManager extends StdManager<Protocolli> {
	/**
	 * restituisce la lista di tutti i protoccolli fino all'alba dei tempi
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Protocolli> list(String oggetto) {
		Session session = null;
		Transaction tx = null;
		List<Protocolli> data = null;
		try {
			
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
			tx = session.beginTransaction();
			
			Criteria query = session.createCriteria(Protocolli.class);
			if (!Controlli.isEmptyString(oggetto)) {
				query.add(Restrictions.like("oggetto", "%" + oggetto + "%"));
			}
			query.addOrder(Order.desc("data"));
			
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
	protected Class<Protocolli> getEntityClass() {
		// TODO Auto-generated method stub
		return Protocolli.class;
	}

	@Override
	protected boolean checkCampiObbligatori(Protocolli object) throws CustomException {
		return true;
	}

	@Override
	protected void operationAfterInsert(Protocolli object, Session session) throws CustomException {
		try {
			
			@SuppressWarnings("unused")
			Query query = session.createSQLQuery(
					"CALL calc_num_prot(:id)")
					.setParameter("id", object.getId());
			
			query.executeUpdate();
			
		} catch (Exception e) {
			throw new CustomException(new StringBuilder().append(e.getMessage()));
		}
	}
}
