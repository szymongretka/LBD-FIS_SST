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

@WebFilter(filterName = "CredentialFilter",
urlPatterns = { "/download" },
initParams = { @WebInitParam(name = "", value = "")})
public class CredentialFilter implements Filter {

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

		chain.doFilter(request, response);

	}
}
