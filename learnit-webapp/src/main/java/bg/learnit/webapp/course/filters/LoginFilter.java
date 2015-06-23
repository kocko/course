package bg.learnit.webapp.course.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.learnit.webapp.course.beans.LoginBean;

/**
 * Filter that checks if the {@LoginBean} has its loggedIn property set to {@code true}. 
 * If it is not set then request is being redirected to the login.xhml page.
 * 
 */
public class LoginFilter implements Filter {

    /**
     * Checks if user is logged in. If not it redirects to the login.xhtml page.
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        
        LoginBean loginBean = (LoginBean) (httpServletRequest.getSession().getAttribute("loginBean"));
        
        if (thereIsALoggedInUser(loginBean)) {
            chain.doFilter(request, response);
        } else {
        	HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            redirectToTheLoginScreen(httpServletRequest, httpServletResponse);
        }

    }

	private boolean thereIsALoggedInUser(LoginBean loginBean) {
		return loginBean != null && loginBean.getLoggedInUser() != null;
	}

	private void redirectToTheLoginScreen(HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
		String contextPath = httpServletRequest.getContextPath();
		response.sendRedirect(contextPath + "/pages/login.jsf");
	}

    public void init(FilterConfig config) throws ServletException { }

    public void destroy() { }
    
}
