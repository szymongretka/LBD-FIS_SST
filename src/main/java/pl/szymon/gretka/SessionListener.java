package pl.szymon.gretka;

import java.util.Calendar;
import java.util.logging.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
	
	private Logger log = Logger.getLogger(getClass().getName());
	
	
	@Override
	  public void sessionCreated(HttpSessionEvent event) {
		
	      log.info("=== Session Created ===");
	      HttpSession session = event.getSession();
	      log.info("Session ID: " + session.getId());
	      
	      long seconds = session.getCreationTime();
	      Calendar c = Calendar.getInstance();
	      c.setTimeInMillis(seconds);
	      log.info("Current date and time: " + String.valueOf(c.getTime()));
	      
	      session.setMaxInactiveInterval(10);
	  }

	  @Override
	  public void sessionDestroyed(HttpSessionEvent se) {
	      log.info("=== Session Destroyed ===");
	  }
}
