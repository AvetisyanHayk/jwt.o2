package be.howest.jwt.o2.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hayk
 */
@WebServlet("/registration-completed.htm")
public class RegistrationCompletedServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String VIEW = "/WEB-INF/JSP/registration-completed.jsp";
    private static final String REDIRECT = "%s/index.htm";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        boolean registrationCompleted = false;
        if (session != null && session.getAttribute("registrationCompleted") != null) {
            registrationCompleted = (boolean) session.getAttribute("registrationCompleted");
        }
        if (registrationCompleted) {
            if (session != null) {
                session.invalidate();
            }
            request.getRequestDispatcher(VIEW).forward(request, response);
        } else {
            response.sendRedirect(String.format(REDIRECT, request.getContextPath()));
        }

    }
}
