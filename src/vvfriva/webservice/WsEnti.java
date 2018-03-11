package vvfriva.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import vvfriva.entity.Ente;
import vvfriva.model.CustomJsonResponse;
import vvfriva.service.EntiService;

@Path("/enti")
public class WsEnti {

	public EntiService entiService = null;
	
	public WsEnti() {
		if (entiService == null) {
			entiService = new EntiService();
		}
	}
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<List<Ente>> list() {
		return entiService.list();
	}
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Ente> save(Ente ente) {
		return entiService.save(ente);
	}
	
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Ente> update(Ente ente) {
		return entiService.update(ente);
	}
	
	@GET
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Ente> delete(@QueryParam("id") Integer id) {
		return entiService.delete(id);
	}
}
