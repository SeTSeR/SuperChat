package chat;

import services.ChatService;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by setser on 05.07.17.
 */
public class WebSocketChatService implements ChatService {
    private Set<ChatServiceListener> listeners;

    public WebSocketChatService() {
        listeners = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    @Override
    public void remove(ChatServiceListener elem) {
        listeners.remove(elem);
    }

    @Override
    public void add(ChatServiceListener elem) {
        listeners.add(elem);
    }

    @Override
    public void sendMessage(String data) {
        for (ChatServiceListener listener: listeners) {
            listener.sendString(data);
        }
    }
}
