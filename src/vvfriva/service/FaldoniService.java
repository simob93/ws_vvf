package vvfriva.service;

import java.util.ArrayList;
import java.util.List;

import vvfriva.entity.Faldone;
import vvfriva.manager.FaldoniManager;
import vvfriva.model.CustomJsonResponse;
import vvfriva.utils.Costanti;

public class FaldoniService {
	
	public FaldoniManager getManager() {
		return new FaldoniManager();
	}

	public CustomJsonResponse<List<Faldone>> list() {
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		List<Faldone> data = null;
		CustomJsonResponse<List<Faldone>> response = null;
		
		try {
			
			data = this.getManager().list();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_OK).toString());
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_KO).append(e.getMessage()).toString());

		} finally {
			response = new CustomJsonResponse<List<Faldone>>(success, messaggi, data);
		}
 		return response;
	}
	public CustomJsonResponse<Faldone> save(Faldone faldone) {
		return this.getManager().actionDb(Costanti.INSERT, faldone, null);
	}
	public CustomJsonResponse<Faldone> update(Faldone faldone) {
		return this.getManager().actionDb(Costanti.UPDATE, faldone, null);
	}
	public CustomJsonResponse<Faldone> delete(Integer id) {
		return this.getManager().actionDb(Costanti.DELETE, null, id);
	}
}
