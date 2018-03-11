package vvfriva.service;

import java.util.ArrayList;
import java.util.List;

import vvfriva.entity.Coordinate;
import vvfriva.entity.Strade;
import vvfriva.manager.StradeManager;
import vvfriva.model.CustomJsonResponse;
import vvfriva.utils.Costanti;

public class StradeService {
	
	public StradeManager getManager() {
		return new StradeManager();
	}
	
	public CustomJsonResponse<List<Coordinate>> list() {
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		List<Coordinate> data = null;
		CustomJsonResponse<List<Coordinate>> response = null;
		
		try {
			data = this.getManager().list();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_OK).toString());
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_KO).append(e.getMessage()).toString());

		} finally {
			response = new CustomJsonResponse<List<Coordinate>>(success, messaggi, data);
		}
		return response;
		
	}

	public CustomJsonResponse<Coordinate> delete(Integer id) {
		// TODO Auto-generated method stub
		return this.getManager().actionDb(Costanti.DELETE, null, id);
	}

	public CustomJsonResponse<Coordinate> update(Coordinate coordinate) {
		// TODO Auto-generated method stub
		return this.getManager().actionDb(Costanti.UPDATE, coordinate, null);
	}

	public CustomJsonResponse<Coordinate> save(Coordinate coordinate) {
		// TODO Auto-generated method stub
		return this.getManager().actionDb(Costanti.INSERT, coordinate, null);
	}

	public CustomJsonResponse<Boolean> importcoordinate() {
		boolean success = true;
		List<String> messaggi = new ArrayList<String>();
		CustomJsonResponse<Boolean> response = null;
		Boolean data = false;
		
		try {
			data = this.getManager().importcoordinate();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_OK).toString());
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			messaggi.add(new StringBuilder().append(Costanti.OPERAZIONE_KO).append(e.getMessage()).toString());

		} finally {
			response = new CustomJsonResponse<Boolean>(success, messaggi, data);
		}
		return response;
	}
}
