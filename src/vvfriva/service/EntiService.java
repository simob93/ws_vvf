package vvfriva.service;

import java.util.ArrayList;
import java.util.List;

import vvfriva.entity.Ente;
import vvfriva.manager.EntiManager;
import vvfriva.model.CustomJsonResponse;
import vvfriva.utils.Costanti;

public class EntiService {
	
	public EntiManager getManager() {
		return new EntiManager();
	}

	public CustomJsonResponse<List<Ente>> list() {
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		List<Ente> data = null;
		CustomJsonResponse<List<Ente>> response = null;
		
		try {
			
			data = this.getManager().list();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_OK).toString());
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_KO).append(e.getMessage()).toString());

		} finally {
			response = new CustomJsonResponse<List<Ente>>(success, messaggi, data);
		}
 		return response;
	}

	public CustomJsonResponse<Ente> save(Ente ente) {
		return this.getManager().actionDb(Costanti.INSERT, ente, null);
	}

	public CustomJsonResponse<Ente> update(Ente ente) {
		return this.getManager().actionDb(Costanti.UPDATE, ente, null);
	}

	public CustomJsonResponse<Ente> delete(Integer id) {
		return this.getManager().actionDb(Costanti.DELETE, null, id);
	}
}
