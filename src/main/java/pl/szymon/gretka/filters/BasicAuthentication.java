package pl.szymon.gretka.filters;

import java.util.List;
import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.szymon.gretka.entity.User;
import pl.szymon.gretka.enums.ROLES;
import pl.szymon.gretka.services.DataInitializer;
import pl.szymon.gretka.services.interfaces.DataBase;
import pl.szymon.gretka.services.qualifiers.Users;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(filterName = "AuthFilter",
urlPatterns = { "/average_statistics" },
initParams = {
@WebInitParam(name = "username", value = ""),
@WebInitParam(name = "password", value = "")
})
public class BasicAuthentication implements Filter {
    
    @Inject
    private DataInitializer dt;

   
    private String realm = "Protected";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //username = filterConfig.getInitParameter("username");
        //password = filterConfig.getInitParameter("password");
        
        String paramRealm = filterConfig.getInitParameter("realm");
        if (paramRealm != null && paramRealm.length() > 0) {
            realm = paramRealm;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
       
        List<User> listOfUsers = dt.getListOfUsers();

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            StringTokenizer st = new StringTokenizer(authHeader);
            if (st.hasMoreTokens()) {
                String basic = st.nextToken();

                if (basic.equalsIgnoreCase("Basic")) {
                    try {
                        String credentials = new String(Base64.getDecoder().decode(st.nextToken()));
                        
                        int p = credentials.indexOf(":");
                        if (p != -1) {
                            String userNameFromHeader = credentials.substring(0, p).trim();
                            String passwordFromHeader = credentials.substring(p + 1).trim();
                            
                            List<String> listOfNames = listOfUsers
                            		.stream()
                            		.map(User::getUsername)
                            		.collect(Collectors.toList());

                            
                            if(listOfNames.indexOf(userNameFromHeader) != -1) {
                            	
                                int index = listOfNames.indexOf(userNameFromHeader);
                                User user = listOfUsers.get(index);
                                
                                if(user.getPassword().equals(passwordFromHeader)) {
                                	ROLES userRole = user.getRole();
                                	if(userRole == ROLES.ADMIN || userRole == ROLES.STATISTIC_MANAGER || userRole == ROLES.STATISTIC_VIEWER) {
                                		user.setLanguage(request.getHeader("Accept-Language").substring(0, 5));
                                		user.setSessionId(request.getSession().getId());
                                		user.setAdressIp(request.getLocalAddr());
                                		System.out.println(user.toString());
                                	} else {
                                		unauthorized(response, "You don't have enough privilages to see this content");
                                	}
                                	

                                } else {
                                	unauthorized(response, "Bad password");
                                }
                            } else {
                            	unauthorized(response, "Bad username");
                            }

                            filterChain.doFilter(servletRequest, servletResponse);
                        } else {
                            unauthorized(response, "Invalid authentication token");
                        }
                    } catch (UnsupportedEncodingException e) {
                        throw new Error("Couldn't retrieve authentication", e);
                    }
                }
            }
        } else {
            unauthorized(response);
        }
        
        
        
        

    }

    @Override
    public void destroy() {
    }

    private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
        response.sendError(401, message);
    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        unauthorized(response, "Unauthorized");
    }

}
