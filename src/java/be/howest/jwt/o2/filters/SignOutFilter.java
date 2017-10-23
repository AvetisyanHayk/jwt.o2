package be.howest.jwt.o2.filters;

import be.howest.jwt.o2.domain.entities.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hayk
 */
@WebFilter("*.htm")
public class SignOutFilter implements Filter {

    private static final String REDIRECT = "%s/signin.htm";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String signOut = request.getParameter("signout");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        if (signOut != null) {
            if (session != null) {
                session.invalidate();
            }
            HttpServletResponse resp = (HttpServletResponse) response; // TODO FindBugs Error?
            resp.sendRedirect(String.format(REDIRECT, req.getContextPath()));
        } else {
            if (session != null) {
                User user = (User)session.getAttribute("user");
                request.setAttribute("user", user);
            }
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
