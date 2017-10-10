package be.howest.o2.servlets;

import be.howest.jwt.o2.data.PartimRepository;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Hayk
 */
@WebServlet("/partims.htm")
public class PartimsServlet extends HttpServlet {

    private static final String VIEW = "/WEB-INF/JSP/partims.jsp";
    
    private final transient PartimRepository partimRepo = new PartimRepository();

    @Resource(name = PartimRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        partimRepo.setDataSource(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

}
