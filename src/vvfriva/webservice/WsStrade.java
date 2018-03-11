package vvfriva.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import vvfriva.entity.Coordinate;
import vvfriva.entity.Strade;
import vvfriva.model.CustomJsonResponse;
import vvfriva.service.StradeService;

@Path("/coordinate")
public class WsStrade {
	
	public StradeService stradeService = null;
	
	public WsStrade() {
		if (stradeService == null) {
			stradeService = new StradeService();
		}
	} 
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<List<Coordinate>> list() {
		return stradeService.list();
	}

	
	@GET
	@Path("importcoordinate")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Boolean> importcoordinate() {
		return stradeService.importcoordinate();
	}
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Coordinate> save(Coordinate cordinate) {
		return stradeService.save(cordinate);
	}
	
	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Coordinate> update(Coordinate cordinate) {
		return stradeService.update(cordinate);
	}
	
	@GET
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Coordinate> delete(@QueryParam("id") Integer id) {
		return stradeService.delete(id);
	}
}
