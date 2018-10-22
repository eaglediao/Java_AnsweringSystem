package com.eaglediao.qa.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SpringWebSocketHandler implements WebSocketHandler {

    private static Logger logger = LoggerFactory.getLogger(SpringWebSocketHandler.class);

    private static final Map<String, WebSocketSession> users;  //Map来存储WebSocketSession，key用USER_ID 即在线用户列表

    //用户标识
    private static final String USER_ID = "employeeID";   //对应监听器从的key


    static {
        users =  new HashMap<String, WebSocketSession>();
    }

    public SpringWebSocketHandler() {}

    /**
     * 连接成功时候，会触发页面上onopen方法
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        System.out.println("成功建立websocket连接!");
        String employeeID = (String) session.getAttributes().get(USER_ID);
        users.put(employeeID,session);
        System.out.println("当前线上用户数量:"+users.size());


    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

//        super.handleMessage(session, message);

        /**
         * 收到消息，自定义处理机制，实现业务
         */
        System.out.println("服务器收到消息："+message);

    }

    /**
     * 关闭连接时触发
     */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.debug("关闭websocket连接");
        String employeeID= (String) session.getAttributes().get(USER_ID);
        System.out.println("用户"+employeeID+"已退出！");
        users.remove(employeeID);
        System.out.println("剩余在线用户"+users.size());
    }

    /**
     * js调用websocket.send时候，会调用该方法
     */



    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        logger.debug("传输出现异常，关闭websocket连接... ");
        String employeeID= (String) session.getAttributes().get(USER_ID);
        users.remove(employeeID);
    }

    public boolean supportsPartialMessages() {

        return false;
    }


    /**
     * 给某个用户发送消息
     *
     * @param employeeID
     * @param message
     */
    public void sendMessageToUser(String employeeID, TextMessage message) {
        for (String id : users.keySet()) {
            if (id.equals(employeeID)) {
                try {
                    if (users.get(id).isOpen()) {
                        users.get(id).sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (String employeeID : users.keySet()) {
            try {
                if (users.get(employeeID).isOpen()) {
                    users.get(employeeID).sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
