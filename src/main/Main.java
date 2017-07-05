package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.ChatServlet;
import servlets.LoginServlet;
import servlets.MainServlet;
import servlets.RegisterServlet;

/**
 * Created by setser on 03.07.17.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        MainServlet mainServlet = new MainServlet();
        LoginServlet loginServlet = new LoginServlet();
        RegisterServlet registerServlet = new RegisterServlet();
        ChatServlet chatServlet = new ChatServlet();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(mainServlet), "/");
        contextHandler.addServlet(new ServletHolder(loginServlet), "/api/login");
        contextHandler.addServlet(new ServletHolder(registerServlet), "/api/register");
        contextHandler.addServlet(new ServletHolder(chatServlet), "/chat");

        Server server = new Server(8080);
        server.setHandler(contextHandler);

        server.start();
        server.join();
    }
}
