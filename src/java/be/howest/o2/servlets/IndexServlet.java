package be.howest.o2.servlets;

import be.howest.jwt.o2.data.PartimRepository;
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
@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {

    private final transient UserRepository userRepo = new UserRepository();
    private final transient PartimRepository partimRepo = new PartimRepository();

    private static final String VIEW = "WEB-INF/JSP/index.jsp";
    private static final String REDIRECT = "%s/signin.htm";

    @Resource(name = UserRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        userRepo.setDataSource(dataSource);
        partimRepo.setDataSource(dataSource);
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
            response.sendRedirect(String.format(REDIRECT, request.getContextPath()));
        } else {
            processRequest(request, response, user);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        request.setAttribute("username", user.getUsername());
        user = userRepo.readWithPartims(user);
        request.setAttribute("user", user);
        request.setAttribute("partims", partimRepo.findAll());
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

}
