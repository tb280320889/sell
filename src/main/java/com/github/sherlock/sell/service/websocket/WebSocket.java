package com.github.sherlock.sell.service.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by TangBin on 2017/9/5.
 */

@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {
  private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();
  private Session session;

  /**
   * @param session
   */
  @OnOpen
  public void onOpen(Session session) {
    this.session = session;
    webSocketSet.add(this);
    log.info("#webSocket information# receive new connection , total: {}", webSocketSet.size());
  }

  /**
   *
   */
  @OnClose
  public void onClose() {
    webSocketSet.remove(this);
    log.info("#webSocket information# connection closed , total: {}", webSocketSet.size());
  }

  /**
   * @param message
   */
  @OnMessage
  public void onMessage(String message) {
    log.info("#webSocket information# receive messages from client : {}", message);
  }

  /**
   * @param message
   */
  public void sendMessage(String message) {
    log.info("#webSocket information# boardCast message , msg={}", message);
    for (WebSocket webSocket : webSocketSet) {
      try {
        webSocket.session.getBasicRemote().sendText(message);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


}
