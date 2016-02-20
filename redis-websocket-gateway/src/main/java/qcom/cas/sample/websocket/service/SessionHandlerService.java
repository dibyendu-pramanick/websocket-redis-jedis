package qcom.cas.sample.websocket.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class SessionHandlerService {

    private Map<String, WebSocketSession> sessionHandlerMap;

    public Map<String, WebSocketSession> getSessionHandlerMap() {
        return sessionHandlerMap;
    }

    public void setSessionHandlerMap( Map<String, WebSocketSession> sessionHandlerMap ) {
        this.sessionHandlerMap = sessionHandlerMap;
    }

    public void putSessionHandlerMap( String deviceId, WebSocketSession session ) {
        if (this.sessionHandlerMap == null) {
            this.sessionHandlerMap = new HashMap<String, WebSocketSession>();
        }
        this.sessionHandlerMap.put(deviceId, session);
    }

    public void removeFromSessionHandlerMap( String deviceId ) {
        if (this.sessionHandlerMap != null) {
            this.sessionHandlerMap.remove(deviceId);
        }
    }

}
