package chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import services.ChatService;

import java.io.IOException;

/**
 * Created by setser on 05.07.17.
 */
@WebSocket
public class ChatWebSocket implements ChatServiceListener {

    private final ChatService service;
    private Session session;

    public ChatWebSocket(ChatService service) {
        this.service = service;
        service.add(this);
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        service.sendMessage(data);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        service.remove(this);
    }

    @Override
    public void sendString(String data) {
        try {
            session.getRemote().sendString(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
