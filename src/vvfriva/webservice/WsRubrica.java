package vvfriva.webservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import vvfriva.entity.Rubrica;
import vvfriva.model.CustomJsonResponse;
import vvfriva.model.Report;
import vvfriva.service.RubricaService;
import vvfriva.utils.Controlli;

@Path("/rubrica")
public class WsRubrica {
	
public RubricaService rubricaService = null;
	
	public WsRubrica() {
		
		if (rubricaService == null) {
			rubricaService = new RubricaService();
		}
	}
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<List<Rubrica>> list() {
		return rubricaService.list();
	}
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Rubrica> save(Rubrica rubrica) {
		return rubricaService.save(rubrica);
	}
	
	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Rubrica> update(Rubrica rubrica) {
		return rubricaService.update(rubrica);
	}
	
	@GET
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Rubrica> delete(@QueryParam("id") Integer id) {
		return rubricaService.delete(id);
	}
	
	@GET
	@Path("print")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Report> print() throws ParseException {
		return rubricaService.print();
	}
}
