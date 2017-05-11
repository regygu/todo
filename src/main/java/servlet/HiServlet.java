import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/hi")
public class HiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        String msg = "Hi ";

        HttpSession session = req.getSession();
        session.setAttribute("msg", msg);
        session.setAttribute("username", name);

        req.getRequestDispatcher("name.jsp").forward(req, resp);
    }
}
