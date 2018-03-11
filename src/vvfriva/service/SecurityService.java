package vvfriva.service;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import vvfriva.entity.Password;

public class SecurityService {
	
	static Logger logger = Logger.getLogger(SecurityService.class.getName());
	/**
	 * metodo per il login lato shiro, viene messo l'utente
	 * in sessione
	 * @param user
	 */
	public void doLoginShiro(Password user) {
		
		Subject currentUser = SecurityUtils.getSubject();
        org.apache.shiro.session.Session session = currentUser.getSession();
        session.setAttribute("user", user.getUsername());
        
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            token.setRememberMe(true);
            try {
                currentUser.login(token);
                logger.info("Utente " + currentUser.getPrincipal().toString() + "in sessione....");
            } catch (UnknownAccountException uae) {
            	logger.error("exception in function  doLoginShiro" + uae.getMessage());
            } catch (IncorrectCredentialsException ice) {
            	logger.error("exception in function  doLoginShiro" + ice.getMessage());
            } catch (LockedAccountException lae) {
            	logger.error("exception in function  doLoginShiro" + lae.getMessage());
            }
            catch (AuthenticationException ae) {
            	ae.printStackTrace();
            }
        }
		
	}
	/**
	 * esegue il logout da shiro, libera la sessione
	 * @return
	 */
	public Boolean doLogoutShiro() {
		boolean uscita = false;
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()){
			try{
				logger.info("Utente " + subject.getPrincipal().toString() + "ha eseguito il logout....");
				subject.logout();
				uscita = true;
				
			}catch(Throwable e){
				logger.error("exception in function doLogoutShiro" + e.getMessage());
			}
		}
		return uscita;
	}
	/**
	 * ritorna se l'utente è loggato o no
	 * @return
	 */
	public boolean isUserLogin() {
		Subject currentUser = SecurityUtils.getSubject();
		return (currentUser != null && currentUser.isAuthenticated());
	}

}
