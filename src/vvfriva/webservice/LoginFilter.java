package vvfriva.webservice;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import vvfriva.service.SecurityService;
import vvfriva.utils.Costanti;
import vvfriva.utils.CustomException;

public class LoginFilter implements Filter   {
	
	private static Logger logger = Logger.getLogger(LoginFilter.class.getName());
	private SecurityService securityService = null;
	
	public LoginFilter() {
		if (this.securityService == null) {
			this.securityService = new SecurityService();
		}
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request =  (HttpServletRequest) arg0;
		HttpServletResponse response =  (HttpServletResponse) arg1;
		if (this.securityService.isUserLogin() || request.getRequestURI().contains("turni")) {
			arg2.doFilter(arg0, arg1);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, Costanti.SESSIONE_KO);
		}
	}
	
//	@Override
//	public ContainerRequest filter(ContainerRequest arg0) {
//		//eseguo il controllo se la sessione per l'utente è ancora attiva oppure no 
//		Subject currentUser = SecurityUtils.getSubject();
//		Session session = currentUser.getSession();
//		if (currentUser.isAuthenticated() && currentUser.getSession() == null) {
//			logger.error("Sessione terminata sarai indirizzo alla pagina di login");
//			throw new WebApplicationException(javax.ws.rs.core.Response.Status.UNAUTHORIZED);
//		}
//		return arg0;
//	}
}

