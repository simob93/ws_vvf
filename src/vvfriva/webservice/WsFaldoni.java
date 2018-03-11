package vvfriva.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import vvfriva.entity.Faldone;
import vvfriva.model.CustomJsonResponse;
import vvfriva.service.FaldoniService;

@Path("/faldoni")
public class WsFaldoni {
	
	public FaldoniService faldoniService = null;
	
	public WsFaldoni() {
		if (faldoniService == null) {
			faldoniService = new FaldoniService();
		}
	}
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<List<Faldone>> list() {
		return faldoniService.list();
	}
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Faldone> save(Faldone faldone) {
		return faldoniService.save(faldone);
	}
	
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Faldone> update(Faldone faldone) {
		return faldoniService.update(faldone);
	}
	
	@GET
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Faldone> delete(@QueryParam("id") Integer id) {
		return faldoniService.delete(id);
	}
}
