package servlets;

import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by setser on 03.07.17.
 */
public class MainServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("username", "SeTSeR");
        response.getWriter().println(PageGenerator.instance().getPage("chat.html", params));
    }
}
