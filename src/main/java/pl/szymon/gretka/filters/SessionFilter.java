package pl.szymon.gretka.filters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "FirstFilter",
urlPatterns = "*",
initParams = { @WebInitParam(name = "", value = "")})
public class SessionFilter implements Filter {

	private ServletContext context;
	private Logger log = Logger.getLogger(getClass().getName());
	
	public void init(FilterConfig config) throws ServletException {
		this.context = config.getServletContext();
		
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		/*String sessionId = req.getSession().getId();
		log.info("Session ID: " + sessionId);
		log.info("Request Format: " + req.getHeader("Accept"));
		log.info("Language: " + req.getHeader("Accept-Language").substring(0, 5));
		
		long startTime = System.nanoTime();*/
		
		chain.doFilter(request, response);
		
		/*long duration = (System.nanoTime() - startTime);
		
		log.info("Response type: " + res.getHeader("Content-Type"));

		log.info("Duration: " + String.valueOf(duration) + " ns");*/
	}
}
