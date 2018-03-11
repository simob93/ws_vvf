package vvfriva.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vvfriva.entity.Vigili;
import vvfriva.manager.VigiliManager;
import vvfriva.model.CustomJsonResponse;
import vvfriva.model.KeyValue;
import vvfriva.model.ModelTurni;
import vvfriva.model.Report;
import vvfriva.utils.Costanti;

public class VigiliService {
	
	public VigiliManager getManager() {
		return new VigiliManager();
	}
	/**
	 * ritorna una lista completa di tutti i vigili, 
	 * di default attivi (asegnati a squadra) e non (in attesa di inserimento)
	 * @param nonAttivi
	 * @return
	 */
	public CustomJsonResponse<List<Vigili>> list(Boolean nonAttivi) {
		
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		List<Vigili> data = null;
		CustomJsonResponse<List<Vigili>> response = null;
		
		try {
			
			data = this.getManager().list(nonAttivi);
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_OK).toString());
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_KO).append(e.getMessage()).toString());

		} finally {
			response = new CustomJsonResponse<List<Vigili>>(success, messaggi, data);
		}
		
 		return response;
	}
	/**
	 * ritorna la prima lettera disponibile, in base alla squadra selezionata
	 * @param idSquadra
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public CustomJsonResponse<List<KeyValue>> getLettere(Integer idSquadra) {
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		List<KeyValue> data = null;
		CustomJsonResponse<List<KeyValue>> response = null;
		
		try {
			
			data = this.getManager().getLettere(idSquadra);
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_OK).toString());
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_KO).append(e.getMessage()).toString());
		} finally {
			response = new CustomJsonResponse<List<KeyValue>>(success, messaggi, data);
		}
		
 		return response;
	}
	/**
	 * 
	 * @param vigile
	 * @return
	 */
	public CustomJsonResponse<Vigili> save(Vigili vigile) {
		return this.getManager().actionDb(Costanti.INSERT, vigile, null);
	}
	/**
	 * 
	 * @param vigile
	 * @return
	 */
	public CustomJsonResponse<Vigili> update(Vigili vigile) {
		return this.getManager().actionDb(Costanti.UPDATE, vigile, null);
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public CustomJsonResponse<Vigili> delete(Integer id) {
		return this.getManager().actionDb(Costanti.DELETE, null, id);
	}
	/**
	 * generazione del turnario relativo alla singola squadra, 
	 * ogni settimana le squadre cambiano, una volta concluso il ciclo, anche i vigili
	 * ruotano, il vigile che faceva il lunedì, si sposta al martedi
	 * @param dataTurno
	 * @param type 
	 * @return
	 */
	public CustomJsonResponse<List<ModelTurni>> getTurni(Date dataTurno, Integer type) {
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		List<ModelTurni> data = null;
		CustomJsonResponse<List<ModelTurni>> response = null;
		
		try {
			
			data = this.getManager().getTurni(dataTurno, type);
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_OK).toString());
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_KO).append(e.getMessage()).toString());
		} finally {
			response = new CustomJsonResponse<List<ModelTurni>>(success, messaggi, data);
		}
 		return response;
	}
	public CustomJsonResponse<Report> print(Date dal, Integer type) throws ParseException {
		
		
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		Report data = null;
		CustomJsonResponse<Report> response = null;
		
		try {
			
			data = this.getManager().print(dal, type);
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_OK).toString());
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_KO).append(e.getMessage()).toString());
		} finally {
			response = new CustomJsonResponse<Report>(success, messaggi, data);
		}
 		return response;
	}
	public CustomJsonResponse<List<Object>> whois(Date dateStart) {
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		List<Object> data = null;
		CustomJsonResponse<List<Object>> response = null;
		
		try {
			
			data = this.getManager().whois(dateStart);
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_OK).toString());
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_KO).append(e.getMessage()).toString());
		} finally {
			response = new CustomJsonResponse<List<Object>>(success, messaggi, data);
		}
 		return response;
	}
	public CustomJsonResponse<Report> print(Boolean nonAttivi) {
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		Report data = null;
		CustomJsonResponse<Report> response = null;
		
		try {
			
			data = this.getManager().print(nonAttivi);
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_OK).toString());
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_KO).append(e.getMessage()).toString());
		} finally {
			response = new CustomJsonResponse<Report>(success, messaggi, data);
		}
 		return response;
	}
	
}
