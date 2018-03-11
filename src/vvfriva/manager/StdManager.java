package vvfriva.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import vvfriva.model.CustomJsonResponse;
import vvfriva.utils.Costanti;
import vvfriva.utils.CustomException;
import vvfriva.utils.HibernateUtils;

public abstract class StdManager<T> {
	/**
	 * 
	 * @return
	 */
	protected abstract Class<T> getEntityClass();
	
	protected abstract void operationAfterInsert(T object, Session session) throws CustomException;
	
	/**
	 * 
	 * @param object
	 * @param session
	 */
	public  void insert(T object, Session session) {
		session.save(object);
	}

	/**
	 * 
	 * @param object
	 * @param session
	 */
	public  void update(T object, Session session) {
		session.merge(object);
	}

	/**
	 * 
	 * @param id
	 * @param session
	 */
	public void soft_delete(Integer id, Session session) {

	}
	
	/**
	 * contralla la validazione dei campi prima di una insert / update / delete
	 * @param object
	 * @return
	 * @throws CustomException 
	 */
	protected abstract  boolean checkCampiObbligatori(T object) throws CustomException;

	public boolean oprationBeforeDelete(T object, Session session) throws CustomException {
		return true;
	}
	
	/**
	 * 
	 * @param operation
	 * @param object
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  CustomJsonResponse<T> actionDb(String operation, T object, Integer id) {
		Session session = null;
		Transaction tx = null;
		List<String> messaggi = new ArrayList<String>();
		boolean success = true;
		StringBuilder sb = new StringBuilder();
		boolean valid = false;
		try {
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
			tx = session.beginTransaction();

			switch (operation) {
			case Costanti.INSERT:
				valid = checkCampiObbligatori(object);
				if (valid) {
					insert(object, session);
					operationAfterInsert(object, session);
				}
				break;
			case Costanti.UPDATE:
				valid = checkCampiObbligatori(object);
				if (valid) {
					update(object, session);
				}
				break;
			case Costanti.DELETE:
				
				T q =  (T) session.get(this.getEntityClass(), id);
				if (q != null) {
					valid = oprationBeforeDelete(q, session);
					if (valid)
						session.delete(q);
				}
				break;
			case Costanti.SOFT_DELETE:
				soft_delete(id, session);
				break;
			default:
				break;
			}
			tx.commit();
			messaggi.add(sb.append(Costanti.OPERAZIONE_OK).toString());

		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			//messaggi.add(sb.append(Costanti.OPERAZIONE_KO).toString());
			messaggi.add(e.getMessage());

		} finally {
			if (session != null) {
				session.close();
			}
		}
		return new CustomJsonResponse<T>(success, messaggi, object);
	}

}
