package servlets;

import chat.ChatServiceListener;
import chat.ChatWebSocket;
import chat.WebSocketChatService;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import services.ChatService;

import javax.servlet.annotation.WebServlet;

/**
 * Created by setser on 05.07.17.
 */
@WebServlet(name = "ChatServlet", urlPatterns = "/chat")
public class ChatServlet extends WebSocketServlet {

    private final static int LOGOUT_TIME = 10 * 60 * 1000;
    private final ChatService chatService;

    public ChatServlet() {
        chatService = new WebSocketChatService();
    }

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        webSocketServletFactory.setCreator((req, resp) -> new ChatWebSocket(chatService));
    }
}
