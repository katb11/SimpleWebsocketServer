package websocket;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws")
public class WSEndpoint {

    @OnOpen
    public void open(final Session session, EndpointConfig config) {
        System.out.println("websocket connected");
    }

    @OnMessage
    public void onMessage(final Session session, final String message) {
        if (session.isOpen()) {
            try {
                session.getBasicRemote().sendText("websocket received: " + message);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("session ended: " + reason.toString());
    }

    @OnError
    public void onError(Session session, Throwable ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}
