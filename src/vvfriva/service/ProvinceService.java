package vvfriva.service;

import java.util.ArrayList;
import java.util.List;

import vvfriva.entity.Province;
import vvfriva.manager.ProvinceManager;
import vvfriva.model.CustomJsonResponse;
import vvfriva.utils.Costanti;

public class ProvinceService {
	
	public ProvinceManager getManager() {
		return new ProvinceManager();
	}
	/**
	 * 
	 * @return
	 */
	public CustomJsonResponse<List<Province>> list() {
		
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		List<Province> data = null;
		CustomJsonResponse<List<Province>> response = null;
		
		try {
			data = this.getManager().list();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_OK).toString());
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_KO).append(e.getMessage()).toString());

		} finally {
			response = new CustomJsonResponse<List<Province>>(success, messaggi, data);
		}
		
 		return response;
	}
	
}
