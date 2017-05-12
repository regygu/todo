import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ugyan on 2017.05.09..
 */

@WebServlet ("/TodoServlet")
public class TodoServlet extends HttpServlet {

    TodoDAO DAO = DatabaseDAO.INSTANCE;
    MemoryDAO memoDAO = MemoryDAO.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String requestType = "";

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("username")) {
                String user = cookie.getValue();
                session.setAttribute("user", user);
            } else if (cookie.getName().equals("requestType")) {
                requestType = cookie.getValue();
            }
        }

        List<Task> list = new ArrayList<>();
        if (requestType.equals("unfinished")) {
            list = DAO.returnUnFinished(session.getAttribute("user").toString());
        } else if (requestType.equals("finished")) {
            list = DAO.returnFinished(session.getAttribute("user").toString());
        } else {
            list = DAO.returnAll(session.getAttribute("user").toString());
        }
        resp.getWriter().print(convertToJSON(list));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = "";
        Cookie[] cookies = req.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                user = cookie.getValue();
            }
        }

        String name = req.getParameter("todo");
        if (name == "") {
            resp.sendRedirect("name.jsp");
        } else {
            DAO.addTask(name, user);
            resp.sendRedirect("name.jsp");
        }
    }

      public String convertToJSON(List<Task> todoList) {

        JSONArray jo = new JSONArray();

        for (Task task : todoList) {
            JSONObject current = new JSONObject();
            current.put("id", task.getId());
            current.put("name", task.getName());
            current.put("completion", String.valueOf(task.getCompletion()));
            jo.add(current);
        }
        return jo.toJSONString();
    }
}


