package vvfriva.service;

import java.util.ArrayList;
import java.util.List;

import vvfriva.manager.SquadreManager;
import vvfriva.model.CustomJsonResponse;
import vvfriva.model.KeyValue;
import vvfriva.utils.Costanti;

public class SquadreService {
	
	public SquadreManager getManager() {
		return new SquadreManager();
	}
	
	@SuppressWarnings("rawtypes")
	public CustomJsonResponse<List<KeyValue>> list() {
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		List<KeyValue> data = null;
		CustomJsonResponse<List<KeyValue>> response = null;
		
		try {
			
			data = this.getManager().list();
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

}
