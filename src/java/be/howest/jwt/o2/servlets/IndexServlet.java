package be.howest.jwt.o2.servlets;

import be.howest.jwt.o2.data.PartimRepository;
import be.howest.jwt.o2.data.UserRepository;
import be.howest.jwt.o2.domain.entities.User;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
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

    private static final long serialVersionUID = 1L;

    private final transient UserRepository userRepo = new UserRepository();
    private final transient PartimRepository partimRepo = new PartimRepository();

    private static final String VIEW = "WEB-INF/JSP/index.jsp";
    private static final String REDIRECT = "%s/signin.htm";
    private static final String REDIRECT_SELF = "%s/index.htm";

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
            user = userRepo.readWithPartims(user);
            request.setAttribute("username", user.getUsername());
            request.setAttribute("partims", partimRepo.findAll());
            request.getRequestDispatcher(VIEW).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("user");
        }
        if (user != null) {
            String[] partimIds = request.getParameterValues("partimid");
            Set<Long> ids = getPartimIds(partimIds);
            user.setPartims(partimRepo.findByIds(ids));
            System.out.println(user);
            userRepo.update(user);
            response.sendRedirect(String.format(REDIRECT_SELF, request.getContextPath()));
        } else {
            response.sendRedirect(String.format(REDIRECT, request.getContextPath()));
        }
    }

    private Set<Long> getPartimIds(String[] partimIds) {
        Set<Long> ids = new HashSet<>();
        for (String id : partimIds) {
            try {
                ids.add(Long.parseLong(id));
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return ids;
    }

    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

}
