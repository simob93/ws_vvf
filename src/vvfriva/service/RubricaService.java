package vvfriva.service;

import java.util.ArrayList;
import java.util.List;

import vvfriva.entity.Comune;
import vvfriva.entity.Rubrica;
import vvfriva.manager.RubricaManager;
import vvfriva.model.CustomJsonResponse;
import vvfriva.model.Report;
import vvfriva.utils.Costanti;

public class RubricaService {
	
	public RubricaManager getManager() {
		return new RubricaManager();
	}
	
	public CustomJsonResponse<List<Rubrica>> list() {
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		List<Rubrica> data = null;
		CustomJsonResponse<List<Rubrica>> response = null;
		
		try {
			data = this.getManager().list();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_OK).toString());
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_KO).append(e.getMessage()).toString());

		} finally {
			response = new CustomJsonResponse<List<Rubrica>>(success, messaggi, data);
		}
		return response;
		
	}

	public CustomJsonResponse<Rubrica> update(Rubrica rubrica) {
		// TODO Auto-generated method stub
		return this.getManager().actionDb(Costanti.UPDATE, rubrica, null);
	}

	public CustomJsonResponse<Rubrica> delete(Integer id) {
		// TODO Auto-generated method stub
		return this.getManager().actionDb(Costanti.DELETE, null, id);
	}

	public CustomJsonResponse<Rubrica> save(Rubrica rubrica) {
		// TODO Auto-generated method stub
		return this.getManager().actionDb(Costanti.INSERT, rubrica, null);
	}

	public CustomJsonResponse<Report> print() {
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		Report data = null;
		CustomJsonResponse<Report> response = null;
		
		try {
			
			data = this.getManager().print();
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
