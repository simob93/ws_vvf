package vvfriva.service;

import java.util.ArrayList;
import java.util.List;

import vvfriva.entity.Comune;
import vvfriva.manager.ComuniManager;
import vvfriva.model.CustomJsonResponse;
import vvfriva.utils.Costanti;

public class ComuniService {

	public ComuniManager getManager() {
		return new ComuniManager();
	}
	/**
	 * 
	 * @return
	 */
	public CustomJsonResponse<List<Comune>> list() {
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		List<Comune> data = null;
		CustomJsonResponse<List<Comune>> response = null;
		
		try {
			data = this.getManager().list();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_OK).toString());
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_KO).append(e.getMessage()).toString());

		} finally {
			response = new CustomJsonResponse<List<Comune>>(success, messaggi, data);
		}
		return response;
		
	}
}
