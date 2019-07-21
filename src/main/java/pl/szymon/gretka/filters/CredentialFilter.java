package pl.szymon.gretka.filters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
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

import pl.szymon.gretka.entity.User;
import pl.szymon.gretka.services.DataInitializer;

@WebFilter(filterName = "CredentialFilter",
urlPatterns = { "/download" },
initParams = { @WebInitParam(name = "", value = "")})
public class CredentialFilter implements Filter {

	@Inject
    private User user; 
	
	private ServletContext context;
	private Logger log = Logger.getLogger(getClass().getName());
	
	public void init(FilterConfig config) throws ServletException {
		this.context = config.getServletContext();
		
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		
		System.out.println("CREDENTIAL FILTER: " + user.getLanguage());
		System.out.println("CREDENTIAL FILTER: " + user.getRole());
		
		
		
		
		
		chain.doFilter(request, response);

	}
}
