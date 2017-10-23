package be.howest.jwt.o2.servlets;

import be.howest.jwt.o2.data.UserRepository;
import be.howest.jwt.o2.domain.entities.User;
import be.howest.jwt.o2.ex.DBException;
import be.howest.jwt.o2.util.PasswordBuilder;
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
@WebServlet("/register.htm")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final String VIEW = "/WEB-INF/JSP/register.jsp";
    private static final String REDIRECT = "%s/registration-completed.htm";
    
    private final transient UserRepository userRepo = new UserRepository();
    
    @Resource(name = UserRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        userRepo.setDataSource(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean errors = false;
        String username = request.getParameter("username");
        if (username == null || !username.matches(User.USERNAME_PATTERN)
                || username.length() > User.MAX_USERNAME_LENGTH) {
            errors = true;
            request.setAttribute("errorUsername", errors);
            System.out.println("Error username");
        }
        String realPassword = request.getParameter("password");
        String realPasswordRepeat = request.getParameter("password-repeat");
        if (realPassword == null || realPasswordRepeat == null
                || !realPassword.matches(User.PASSWORD_PATTERN)
                || !realPassword.equals(realPasswordRepeat)) {
            errors = true;
            if (realPassword != null && !realPassword.equals(realPasswordRepeat)) {
                request.setAttribute("errorPasswordsMismatch", errors);
            } else {
                request.setAttribute("errorPassword", errors);
            }
        }
        if (!errors) {
            PasswordBuilder pb = new PasswordBuilder();
            String securePassword = pb.createHashedPassword(realPassword);
            boolean created = false;
            try {
                created = userRepo.save(username, securePassword);
            } catch (DBException ex) {
                System.out.println(ex.getMessage());
            }
            if (created) {
                HttpSession session = request.getSession();
                session.setAttribute("registrationCompleted", true);
                response.sendRedirect(String.format(REDIRECT, request.getContextPath()));
            } else {
                errors = true;
                request.setAttribute("errorUserExists", errors);
                doGet(request, response);
            }
        } else {
            doGet(request, response);
        }
    }
    
    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

}
