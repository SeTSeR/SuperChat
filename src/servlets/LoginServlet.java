package servlets;

import accounts.AccountException;
import accounts.MapAccountsService;
import accounts.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by setser on 03.07.17.
 */
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        MapAccountsService accountsService = MapAccountsService.instance();
        if(login == null || password == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {
            User user = accountsService.getUserByName(login);
            if(!password.equals(user.getPassword())) {
                response.getWriter().println("Wrong login or password");
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
            else {
                accountsService.addSession(user, request.getSession().getId());
                response.getWriter().println("You are logged in successfully. Go back to the main page");
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } catch(AccountException e) {
            response.getWriter().println("Wrong login or password");
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
