package pl.szymon.gretka;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(getClass().getName());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.print("Hello FIS");
	
		
		 HttpSession session = request.getSession(false);
	     if (session == null) {
	          log.info("Creating new session in the servlet");
	          session = request.getSession(true);
	          log.info("Session created in the servlet");

	      }

	      response.setContentType("text/html");
		
		Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
            out.print(" header name: " + key + " header value: " + value);
            out.print("\n");
        }
        
        String sessionId = request.getSession().getId();
        out.print("Session id: " + sessionId);
        
	}
	
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/

}
