package servlets;

import accounts.AccountException;
import accounts.DBAccountsService;
import accounts.MapAccountsService;
import accounts.User;
import services.AccountsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by setser on 03.07.17.
 */
public class RegisterServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        AccountsService accountsService = DBAccountsService.instance();
        if(login == null || password == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {
            accountsService.addUser(new User(login, password));
            response.getWriter().println("You are successfully registered! Now go back to the main page and login");
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (AccountException e) {
            response.getWriter().println("There is user with this login!");
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        response.sendRedirect("/");
    }

}
