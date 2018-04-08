package vvfriva.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import vvfriva.entity.Comune;
import vvfriva.entity.Province;
import vvfriva.entity.Vigili;
import vvfriva.model.CustomJsonResponse;
import vvfriva.service.ComuniService;
import vvfriva.service.ProvinceService;
import vvfriva.service.VigiliService;
import vvfriva.utils.Costanti;

@Path("/general")
public class WsGenerale {
	/**
	 * 
	 */
	public ProvinceService provinceSrvice = null;
	public ComuniService comuniService = null;
	public VigiliService vigiliService = null;
	
	public WsGenerale() {
		if (provinceSrvice == null) {
			provinceSrvice = new ProvinceService();
		}
		if (comuniService == null) {
			comuniService = new ComuniService();
		}
		if (vigiliService == null) {
			vigiliService = new VigiliService();
		}
	}
	
	@GET
	@Path("province/list")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<List<Province>> getProvince() {
		return provinceSrvice.list();
	}
	
	@GET
	@Path("comuni/list")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<List<Comune>> getComuni() {
		return comuniService.list();
	}
	
	@GET
	@Path("checkbirthdays")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<List<Vigili>> checkbirthday() {
		return vigiliService.checkbirthday();
	}
	
	@GET
	@Path("session")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Boolean> getSession() {

		boolean success = true;
		List<String> msg = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		Boolean login = true;
		try {
			org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
			if (!currentUser.isAuthenticated()) {
				login = false;
				msg.add(sb.append(Costanti.USER_OUT_SESSION).toString());
				
			} else {
				msg.add(sb.append(Costanti.USER_IN_SESSION).append(currentUser.getPrincipal().toString()).toString());
			}
			
		} catch (Exception e) {
			success = false;
			msg.add(sb.append(Costanti.OPERAZIONE_KO).append(e.getMessage()).toString());
		}
		return new CustomJsonResponse<Boolean>(success, msg, login);
	}

}
