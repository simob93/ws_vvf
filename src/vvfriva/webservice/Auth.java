//package vvfriva.webservice;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.criterion.Restrictions;
//
//import vvfriva.entity.Password;
//import vvfriva.utils.HibernateUtils;
//
//@WebServlet("/auth")
//public class Auth  extends HttpServlet {
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("/vvf?login=true").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    	String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        Password user = null;
//        Session session = null;
//        Transaction tx = null;
//       
//        
//        try {
//			
//        	session = HibernateUtils.getSessionAnnotationFactory().openSession();
//			tx = session.beginTransaction();
//			Criteria query = session.createCriteria(Password.class);
//			query.add(Restrictions.eq("username", username));
//			query.add(Restrictions.eq("password", password));
//			user = (Password) query.uniqueResult();
//			tx.commit();
//			if (user != null) {
//				 request.getSession(true).setAttribute("user", user.getNome());
//				 response.sendRedirect("vvf");
//				 response.getWriter().write("utente in sessione");
//				 response.getWriter().close();
//				
//			} else {
//				 response.getWriter().write("Username o Password errati");
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			if (tx != null) {
//				tx.rollback();
//			}
//		} finally {
//			if (session != null) {
//				session.close();
//			}
//		}
//    }
//}
package vvfriva.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import vvfriva.entity.Password;
import vvfriva.model.CustomJsonResponse;
import vvfriva.service.SecurityService;
import vvfriva.utils.Costanti;
import vvfriva.utils.HibernateUtils;

@Path("/auth")
public class Auth  {
	
	private SecurityService securityService = null;
	public Auth() {
		
		if (this.securityService == null) {
			this.securityService = new SecurityService();
		}
	}
	
	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Password> login(@FormParam("username") String username, @FormParam("password") String password) {
       
		Password user = null;
        Session session = null;
        Transaction tx = null;
        boolean success = true;
        List<String> message = new ArrayList<String>();
        try {
			/*
			 * 	eseguo il login lato server, lato db, eseguo il controllo 
			 * 	su username e password  entrambi devono essere obbligatori
			 */
        	session = HibernateUtils.getSessionAnnotationFactory().openSession();
			tx = session.beginTransaction();
			
			Criteria query = session.createCriteria(Password.class);
			query.add(Restrictions.eq("username", username));
			query.add(Restrictions.eq("password", password));
			user = (Password) query.uniqueResult();
			tx.commit();
			if (user != null) {				
				message.add(new StringBuilder(Costanti.LOGIN_OK).toString());
				this.securityService.doLoginShiro(user); //passo al login della sessione shiro
				
			} else {
				success = false;
				message.add(new StringBuilder(Costanti.LOGIN_KO).toString());
			}
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
        return new CustomJsonResponse<Password>(success, message, null);
	}
	
	
	@GET
	@Path("logout")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomJsonResponse<Boolean> logout() {
		boolean exit = this.securityService.doLogoutShiro();
		return new CustomJsonResponse<Boolean>(exit, null, exit);
        
	}
}

