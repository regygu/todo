import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ugyan on 2017.05.10..
 */

@WebServlet (urlPatterns = {"/EditServlet", "/EditServlet/*"})
public class EditServlet extends HttpServlet {

    MemoryDAO memoDAO = MemoryDAO.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        memoDAO.toggleStatus(id);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer index = Integer.valueOf(req.getRequestURI().lastIndexOf("/"));
        Integer id = Integer.valueOf(req.getRequestURI().substring(index + 1));
        memoDAO.deleteTask(id);
    }
}
