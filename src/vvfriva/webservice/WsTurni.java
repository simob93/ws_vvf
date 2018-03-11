package vvfriva.webservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import vvfriva.model.CustomJsonResponse;
import vvfriva.model.ModelTurni;
import vvfriva.model.Report;
import vvfriva.service.VigiliService;
import vvfriva.utils.Controlli;

@Path("/turni")
public class WsTurni {
	
	private VigiliService vigiliService;
	
	public WsTurni() {
		if(vigiliService == null) {
			vigiliService = new VigiliService();
		}
	}
	
	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<List<ModelTurni>> getTurni(@QueryParam("data") String dataTurno, @QueryParam("type") Integer type) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		return vigiliService.getTurni(sdf.parse(dataTurno), type);
	}
	
	@GET
	@Path("print")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Report> print(@QueryParam("dal") String dal, @QueryParam("type") Integer type) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		Date dataDal = !Controlli.isEmptyString(dal) ? sdf.parse(dal) : null;
		return vigiliService.print(dataDal, type);
	}
	
	@GET
	@Path("whois")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<List<Object>> whois(@QueryParam("data") String data) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		Date dateStart = !Controlli.isEmptyString(data) ? sdf.parse(data) : null;
		return vigiliService.whois(dateStart);
	}
}
