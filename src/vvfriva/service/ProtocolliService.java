package vvfriva.service;

import java.util.ArrayList;
import java.util.List;

import vvfriva.entity.Protocolli;
import vvfriva.manager.ProtocolliManager;
import vvfriva.model.CustomJsonResponse;
import vvfriva.utils.Costanti;

public class ProtocolliService {
	
	public ProtocolliManager getManager() {
		return new ProtocolliManager();
	}
	
	public CustomJsonResponse<List<Protocolli>> list(Integer limit, Integer start, String oggetto) {
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		List<Protocolli> data = null;
		List<Protocolli> dataPaging = new ArrayList<Protocolli>();
		CustomJsonResponse<List<Protocolli>> response = null;
		
		try {
			
			data = this.getManager().list(oggetto);
			for(int i=0; i< data.size(); i++) {
				if (i >= start) {
					dataPaging.add(data.get(i));
				}
				if (dataPaging.size() == limit) break;
			}
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_OK).toString());
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_KO).append(e.getMessage()).toString());
		} finally {
			response = new CustomJsonResponse<List<Protocolli>>(success, messaggi, dataPaging, data.size());
		}
		
 		return response;
	}

	public CustomJsonResponse<Protocolli> save(Protocolli protocollo) {
		return this.getManager().actionDb(Costanti.INSERT, protocollo, null);
	}
	
	public CustomJsonResponse<Protocolli> update(Protocolli protocollo) {
		return this.getManager().actionDb(Costanti.UPDATE, protocollo, null);
	}
	
	public CustomJsonResponse<Protocolli> delete(Integer id) {
		return this.getManager().actionDb(Costanti.DELETE, null, id);
	}

}
