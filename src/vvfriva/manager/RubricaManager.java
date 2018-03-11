package vvfriva.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import vvfriva.entity.Rubrica;
import vvfriva.model.Report;
import vvfriva.utils.CustomException;
import vvfriva.utils.HibernateUtils;
import vvfriva.utils.StandardUtils;

public class RubricaManager extends StdManager<Rubrica> {
	
	private Session session = null;
	private Session getDbSession() {
		if (session == null) {
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
		}
		return session;
	}
	
	@SuppressWarnings("unchecked")
	public List<Rubrica> list() {
		Session session = null;
		Transaction tx = null;
		List<Rubrica> data = null;
		try {
			
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
			tx = session.beginTransaction();
			
			Criteria query = session.createCriteria(Rubrica.class);
			query.addOrder(Order.asc("nome"));
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
	protected Class<Rubrica> getEntityClass() {
		// TODO Auto-generated method stub
		return Rubrica.class;
	}

	@Override
	protected void operationAfterInsert(Rubrica object, Session session) throws CustomException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean checkCampiObbligatori(Rubrica object) throws CustomException {
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressWarnings("unchecked")
	public Report print() {
		Transaction tx = null;
		Report data = null;
		try {
			tx = this.getDbSession().beginTransaction();
			Criteria query = this.getDbSession().createCriteria(Rubrica.class);
			query.addOrder(Order.asc("nome"));
			List<Rubrica> listRubrica = query.list();
			
			if (listRubrica.size() > 0) {
				HashMap<String, Object> parameters = new HashMap<String, Object>();
	            parameters.put("data", new JRBeanCollectionDataSource(listRubrica));
				data = StandardUtils.doPrint(parameters, "rubrica", null);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return data;
	}
}
