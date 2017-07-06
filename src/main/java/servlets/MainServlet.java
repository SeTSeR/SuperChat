package servlets;

import accounts.AccountException;
import accounts.DBAccountsService;
import accounts.MapAccountsService;
import accounts.User;
import services.AccountsService;
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
        AccountsService accountsService = DBAccountsService.instance();
        User user;
        try {
            user = accountsService.getUserBySessionId(request.getSession().getId());
            HashMap<String, Object> params = new HashMap<>();
            params.put("username", user.getLogin());
            response.getWriter().println(PageGenerator.instance().getPage("chat.html", params));
        } catch(AccountException e) {
            response.getWriter().println(PageGenerator.instance().getPage("reglogin.html", new HashMap<>()));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println(PageGenerator.instance().getPage("reglogin.html", new HashMap<>()));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}