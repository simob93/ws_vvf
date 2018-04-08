package vvfriva.webservice;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import vvfriva.entity.Vigili;
import vvfriva.model.CustomJsonResponse;
import vvfriva.model.KeyValue;
import vvfriva.model.Report;
import vvfriva.service.VigiliService;

@Path("/vigili")
public class WsVigili {
	
	public VigiliService vigiliService = null;
	
	public WsVigili() {
		
		if (vigiliService == null) {
			vigiliService = new VigiliService();
		}
	}
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<List<Vigili>> list(@DefaultValue("false") @QueryParam("nonAttivi") Boolean nonAttivi) {
		return vigiliService.list(nonAttivi);
	}
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Vigili> save(Vigili vigile) {
		return vigiliService.save(vigile);
	}
	
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Vigili> update(Vigili vigile) {
		return vigiliService.update(vigile);
	}
	
	@GET
	@Path("getLettere")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<List<KeyValue>> getLettere(@QueryParam("idSquadra") Integer idSquadra) {
		return vigiliService.getLettere(idSquadra);
	}
	
	@GET
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Vigili> delete(@QueryParam("id") Integer id) {
		return vigiliService.delete(id);
	}
	
	@GET
	@Path("print")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Report> print(@DefaultValue("false") @QueryParam("nonAttivi") Boolean nonAttivi) throws ParseException {
		return vigiliService.print(nonAttivi);
	}

}
