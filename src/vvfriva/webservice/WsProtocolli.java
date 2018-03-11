package vvfriva.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import vvfriva.entity.Protocolli;
import vvfriva.model.CustomJsonResponse;
import vvfriva.service.ProtocolliService;

@Path("/protocolli")
public class WsProtocolli {
	
	public ProtocolliService protocolliService = null;
	
	public WsProtocolli() {
		if (protocolliService == null) {
			protocolliService = new ProtocolliService();
		}
	}
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<List<Protocolli>> list(
			@QueryParam("limit") Integer limit, 
			@QueryParam("start") Integer start,
			@QueryParam("oggetto") String oggetto) {
		return protocolliService.list(limit, start, oggetto);
	}
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Protocolli> save(Protocolli protocollo) {
		return protocolliService.save(protocollo);
	}
	
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Protocolli> update(Protocolli protocollo) {
		return protocolliService.update(protocollo);
	}
	
	@GET
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Protocolli> delete(@QueryParam("id") Integer id) {
		return protocolliService.delete(id);
	}
}
