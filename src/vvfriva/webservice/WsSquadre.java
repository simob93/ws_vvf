package vvfriva.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import vvfriva.model.CustomJsonResponse;
import vvfriva.model.KeyValue;
import vvfriva.service.SquadreService;

@Path("/squadre")
public class WsSquadre {
	
public SquadreService squadreService = null;
	
	public WsSquadre() {
		
		if (squadreService == null) {
			squadreService = new SquadreService();
		}
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<List<KeyValue>> list() {
		return squadreService.list();
	}
}
