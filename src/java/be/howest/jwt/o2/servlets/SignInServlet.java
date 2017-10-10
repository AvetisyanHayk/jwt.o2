package be.howest.jwt.o2.servlets;

import be.howest.jwt.o2.data.UserRepository;
import be.howest.jwt.o2.domain.entities.User;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Hayk
 */
@WebServlet("/signin.htm")
public class SignInServlet extends HttpServlet {

    private static final String GET_SELF = "%s/signin.htm";
    private static final String REDIRECT = "%s/index.htm";
    private static final String VIEW = "WEB-INF/JSP/signin.jsp";
    
    private final transient UserRepository userRepository = new UserRepository();
    
    @Resource(name = UserRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        userRepository.setDataSource(dataSource);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("user");
        }
        if (user == null) {
            if (session != null) {
                session.invalidate();
            }
            request.getRequestDispatcher(VIEW).forward(request, response);
        } else {
            response.sendRedirect(String.format(REDIRECT, request.getContextPath()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("user");
            if (user == null) {
                session.invalidate();
            } else {
                response.sendRedirect(String.format(REDIRECT, request.getContextPath()));
            }
        }
        String username = request.getParameter("username");
        user = userRepository.findByUsername(username);
        String password = request.getParameter("password");
        if (user.passwordMatch(password)) {
            session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(String.format(REDIRECT, request.getContextPath()));
        } else {
            // TODO user not found
            request.getRequestDispatcher(VIEW).forward(request, response);
        }
    }

    private void redirectToIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
