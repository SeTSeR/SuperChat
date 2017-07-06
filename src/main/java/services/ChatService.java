package services;

import chat.ChatServiceListener;

/**
 * Created by setser on 05.07.17.
 */
public interface ChatService {
    void remove(ChatServiceListener elem);
    void add(ChatServiceListener elem);
    void sendMessage(String data);
}
