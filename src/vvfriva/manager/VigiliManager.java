package vvfriva.manager;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ResultTransformer;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import vvfriva.entity.Squadre;
import vvfriva.entity.Vigili;
import vvfriva.model.KeyValue;
import vvfriva.model.ModelTurni;
import vvfriva.model.Report;
import vvfriva.model.ReportTurnario;
import vvfriva.model.ReportTurniTestata;
import vvfriva.utils.Controlli;
import vvfriva.utils.Costanti;
import vvfriva.utils.CustomException;
import vvfriva.utils.HibernateUtils;
import vvfriva.utils.StandardUtils;

public class VigiliManager extends StdManager<Vigili> {
	/**
	 * restituisce la lista di tutti i vigili
	 * @return
	 */
	public SquadreManager squadreManager() {
		return new SquadreManager();
	}
	
	static Logger logger = Logger.getLogger(VigiliManager.class.getName());
	
	//static Logger logger = Logger.getLogger("VigiliManager");
	
	@SuppressWarnings("unchecked")
	public List<Vigili> list(Boolean nonAttivi) {
		Session session = null;
		Transaction tx = null;
		List<Vigili> data = null;
		try {
			
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
			tx = session.beginTransaction();
			
			Criteria query = session.createCriteria(Vigili.class);
			if (nonAttivi == true) {
				//query.setFetchMode("squadra", FetchMode.JOIN);
				query.add(Restrictions.isNull("squadra"));
			} 
			data = query.list();
			query.addOrder(Order.asc("cognome"));
			tx.commit();
			
		} catch (Exception e) {
			logger.error("exception in function: list " + e.getMessage());
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
	/**
	 * 
	 * @param idSquadra
	 * @return
	 * @throws CustomException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<KeyValue> getLettere(Integer idSquadra) throws CustomException {
		Session session = null;
		Transaction tx = null;
		List<Vigili> data = null;
		StringBuilder sb = new StringBuilder();
		String[] lettere = StandardUtils.getLettereAlfabeto();
		List<KeyValue> listLettere = new ArrayList<KeyValue>();
		
		if (!Controlli.isValidId(idSquadra)) {
			throw new CustomException(sb.append(Costanti.INVALID_ID));
		}
		
		try {
			
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
			tx = session.beginTransaction();
			
			Criteria query = session.createCriteria(Vigili.class);
			query.createAlias("squadra", "s");
			
			query.add(
				Restrictions.eq("s.id", idSquadra)
			);
			query.addOrder(Order.asc("letteraVigile"));
						
			data = query.list();
			tx.commit();
			
			if (!Controlli.isEmptyList(data)) {
				
				for (int i = 0; i < lettere.length; i++) {
					String lettera = lettere[i];
					boolean trovato = false;
					for (Vigili vigile : data) {
						if (Controlli.stringCompareTo(lettera, vigile.getLetteraVigile(), false) == 0) {
							trovato = true;
							break;
						} 
					}
					if (!trovato) {
						listLettere.add(new KeyValue<T>(null, lettera, null));
					}
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
		return listLettere;
	}
	
	@Override
	public boolean oprationBeforeDelete(Vigili object, Session session) throws CustomException {
		StringBuilder sb = new StringBuilder();
		boolean valid = true;
		try {
			if (object.getSquadra() != null) {
				Squadre sq = (Squadre) session.get(Squadre.class, object.getSquadra().getId());
				if (sq != null) {
					valid = false;
					throw new CustomException(
						sb.append("Impossibile eliminare il vigile perche risulta assegnato alla squadra").append(":").append(sq.getId().toString())
					);
				}
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(new StringBuilder().append(e.getMessage()));
		}
		return valid;
	}
	
	@Override
	protected Class<Vigili> getEntityClass() {
		return Vigili.class;
	}
	
	@Override
	protected boolean checkCampiObbligatori(Vigili object) throws CustomException {
		StringBuilder sb = new StringBuilder();
		boolean isValid = true;
		if (Controlli.stringCompareTo(object.getNome(), "", false) == 0) {
			throw new CustomException(sb.append("Campo nome obbligatorio"));
		}
		if (Controlli.stringCompareTo(object.getCognome(), "", false) == 0) {
			throw new CustomException(sb.append("Campo cognome obbligatorio"));
		}
		if (object.getDataNascita() == null) {
			throw new CustomException(sb.append("Campo data di nascita obbligatorio"));
		}
		if (Controlli.stringCompareTo(object.getGrado(), "", false) == 0) {
			throw new CustomException(sb.append("Campo grado obbligatorio"));
		}
		return isValid;
	}
	
	@Override
	protected void operationAfterInsert(Vigili object, Session session) {
		// TODO Auto-generated method stub
		
	}
	
	public String[] getTurniVigili() {

		String[] turni = { "LUNEDI", "MARTEDI", "MERCOLEDI", "GIOVEDI", "VENERDI", "SABATO", "DOMENICA", "SABATO M",
				"SABATO P", "DOMENICA M", "DOMENICA P" };
		return turni;
	}
	
	public List<ModelTurni> getTurni(Date dataInizio, Integer type) throws ParseException {
		
		List<ModelTurni> turni = null;
		
		try {
			Date dal = StandardUtils.startOfWeek(dataInizio),
				 al = null;
			
			switch (type) {
			case 1:
				//calcolo settimanale
				al = StandardUtils.endOfWeek(dataInizio);
				break;
			case 2:
				//calcolo mensile
				al = StandardUtils.endOfWeek(StandardUtils.addMonth(dal, 1));
				break;
			case 3:
				//calcolo trimestrale
				al = StandardUtils.endOfWeek(StandardUtils.addMonth(dal, 3));
				break;
			case 4:
				//calcolo 6 mesi
				al = StandardUtils.endOfWeek(StandardUtils.addMonth(dal, 6));
				break;
			case 5:
				//calcolo 1 anno
				al = StandardUtils.endOfWeek(StandardUtils.addYear(dal, 1));
				break;

			default:
				break;
			}
			turni = this.calcolaTurni(dal, al);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return turni;
	}
	private List<ModelTurni> calcolaTurni(Date dal, Date al) {
		
		Session session = null;
		Transaction tx = null;
		List<ModelTurni> data = new ArrayList<ModelTurni>();
		int totSquadre = 5;	
		String[] turni_sett = getTurniVigili();
		StringBuilder sb = new StringBuilder();
		//traduzione della data nel formato dd/mm/yyy
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyy");

		try {
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
			tx = session.beginTransaction();
			
			while (al.after(dal)) {
				
				sb = new StringBuilder();
				SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
				
				Date  startDate = sdf.parse("2013-04-08");
				Date temp = null;
				Date appoggio = dal;
				
				int nrWeek = StandardUtils.numberOfWeek(dal, startDate),
					ciclo = (int) (Math.floor(nrWeek / totSquadre) % turni_sett.length), //quante volte si sono iterate le sqaudre
					squadraTurno = (nrWeek % totSquadre) + 1;
				
				sb.append("Dal: ").append(sdf2.format(dal)).append(" Al: ").append(sdf2.format(StandardUtils.endOfWeek(dal)));
				
				//estrapolo le informazioni relative allla squadra di turna per quella settimana
				Criteria query = session.createCriteria(Vigili.class);
				query.createAlias("squadra", "s");
				query.add(Restrictions.eq("s.id", squadraTurno));
				query.addOrder(Order.asc("letteraVigile"));
				@SuppressWarnings("unchecked")
				List<Vigili> listVigili = query.list();
				String capoSquadra = "";
				//-----------------------------------------------------------------------
				for (Vigili v: listVigili) {
					if (v.getCapoSquadra().compareTo(Costanti.CAPOSQUADRA) == 0) {
						capoSquadra = v.getNome() + " " + v.getCognome();
					}
				}
				 
				int numeroVigili =  listVigili.size();
				int index = 0; 
				
				for (int i= 0, len = turni_sett.length; i< len; i++) {
					temp = null;
					
					index = (i - ciclo);
					index = (index % numeroVigili);
					
					if (index < 0)
						index = index + numeroVigili;
					

					Vigili vigile = listVigili.get(index);
					if (i == 7 || i == 8) {
						temp = StandardUtils.getSaturday(appoggio);

					} else if (i == 9 || i == 10) {
						temp = StandardUtils.endOfWeek(appoggio);
					}
					//costruisco il modello
					data.add(
						new ModelTurni(
							vigile.getNome() + " " + vigile.getCognome(), 
							temp !=null ? sdf2.format(temp) : sdf2.format(dal), 
							turni_sett[i], 
							vigile.getSquadra().getNumeroSquadra(),
							vigile.getPatente(),
							vigile.getLetteraVigile(),
							vigile.getCercaPersone(),
							vigile.getSquadra() !=null ? vigile.getSquadra().getId() : null,
							capoSquadra,
							sb.toString()
						)
					);
					
					if (i <= 6) {
						dal = StandardUtils.addDay(dal, 1);
					}
				}
				appoggio = dal;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return data;
	}
	/**
	 * stampa del turnario, date in ingresso due date
	 * @param dal
	 * @param type
	 * @return
	 * @throws ParseException
	 * @throws IOException 
	 * @throws CustomException 
	 */
	public Report print(Date dal, Integer type) throws ParseException, IOException, CustomException {
		List<ModelTurni> turni = this.getTurni(dal, type);
		Report data = null;
		//mi scorro i turni
		List<ReportTurnario> litRt = new ArrayList<>();
		List<ModelTurni> tmp = new ArrayList<>();
		ReportTurnario rt = new ReportTurnario();
		ModelTurni temp = null;
		int idx = 0;
		for (ModelTurni mt : turni) {
			
			boolean cambio = (temp != null && mt.getIdSquadra() != temp.getIdSquadra());
			
			if (cambio) {
				
				rt.setTurni(tmp);
				rt.setPeriodo(tmp.get(0).getPeriodoSett());
				rt.setNumeroSquadra(tmp.get(0).getNumeroSquadra());
				rt.setCapoSquadra(tmp.get(0).getCapoSquadra());
				
				tmp = new ArrayList<>();
				litRt.add(rt);
				
				rt = new ReportTurnario();
			} else if (idx == turni.size() -1) {
				
				tmp.add(mt);
				
				rt.setPeriodo(tmp.get(0).getPeriodoSett());
				rt.setNumeroSquadra(tmp.get(0).getNumeroSquadra());
				rt.setCapoSquadra(tmp.get(0).getCapoSquadra());
				rt.setTurni(tmp);
				
				tmp = new ArrayList<>();
				litRt.add(rt);
				break;
			}
			tmp.add(mt);
			
			temp = mt;
			idx++;
		}
		
		
		
		if (turni.size() > 0) {
			List<ReportTurniTestata> testata = new ArrayList<>();
			testata.add(new ReportTurniTestata(litRt));
			data = StandardUtils.doPrint(new HashMap<>(), "turnario", testata);
		}
		return data;
	}
	/**
	 * metodo che ritorna, informazioni tipo:
	 * squadra di turno, periodo di turno, caposquadra,
	 * @param <T>
	 * @param data
	 * @return
	 * @throws ParseException 
	 */
	public List<Object> whois(Date data) throws ParseException {
		
		
		Session session = null;
		Transaction tx = null;
		List<Object> result = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyy");
		Date  startDate = sdf.parse("2013-04-08");
		
		int totSquadre = this.squadreManager().list().size();
		
		int nrWeek = StandardUtils.numberOfWeek(StandardUtils.startOfWeek(data), startDate),
			squadraTurno = (nrWeek % totSquadre) + 1;
		
		try {
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
			tx = session.beginTransaction();
			
			Criteria query = session.createCriteria(Vigili.class);
			query.createAlias("squadra", "s");
			query.add(Restrictions.eq("s.id", squadraTurno));
			query.add(Restrictions.eq("capoSquadra", 1));
			query.addOrder(Order.asc("letteraVigile"));
			@SuppressWarnings("unchecked")
			List<Vigili> listVigili = query.list();
			if (listVigili.size() > 0) {
				
				//informazioni legata alla squadra di turno per il periodo indicato
				result.add(
					sb.append("Squadra di turno: ")
					.append(squadraTurno)
				);
				sb = new StringBuilder();
				//informazioni legate al periodo del turno
				result.add(
					sb.append(" Dal ")
					.append(sd.format(StandardUtils.startOfWeek(data)))
					.append(" Al ")
					.append(sd.format(StandardUtils.endOfWeek((data))))
				);
				sb = new StringBuilder();
				//informazioni legate al capo squadra
				result.add(
					sb.append("Capo squadra: ")
					.append(listVigili.get(0).getNome())
					.append(" ")
					.append(listVigili.get(0).getCognome())
				);
			}
			
		} catch (Exception e) {
			logger.error("exception in fn: whois");
			if (tx != null) tx.rollback();
			e.printStackTrace();
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
		
	}
	public Report print(Boolean nonAttivi) {

		Report data = null;
		try {
			List<Vigili> listVigili = this.list(nonAttivi);
			
			if (listVigili.size() > 0) {
				HashMap<String, Object> parameters = new HashMap<String, Object>();
	            parameters.put("data", new JRBeanCollectionDataSource(listVigili));
				data = StandardUtils.doPrint(parameters, "vigili", null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}	
	/**
	 * metodo per l'estrapolazione dei compleanni 
	 * relativi ai vigili
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Vigili> checkbirthday() {
		Transaction tx = null;
		Session session = null;
		List<Vigili> data = null;
		try {
			session = HibernateUtils.getSessionAnnotationFactory().openSession();
			tx = session.beginTransaction();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT nome as nome, cognome as cognome, datadinascita as dataNascita ")
			  .append("FROM vigile ")
			  .append("WHERE MONTH(datadinascita)=MONTH(CURDATE()) AND DAY(datadinascita)=DAY(CURDATE())");
			
			data = session.createSQLQuery(sb.toString())
				.setResultTransformer(new AliasToBeanResultTransformer(Vigili.class)).list();
						
		
		} catch (Exception e) {
			logger.error("exception in function: checkbirthday " + e.getMessage());
			e.printStackTrace();
			if (tx != null) tx.rollback();
		} finally {
			if (session != null) session.close();
		}
		return data;
	}
	
}
