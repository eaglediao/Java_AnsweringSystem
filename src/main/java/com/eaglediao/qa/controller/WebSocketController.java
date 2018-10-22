package com.eaglediao.qa.controller;
import com.eaglediao.qa.util.ContextUtils;
import com.eaglediao.qa.websocket.SpringWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhangwenchao on 2017/11/20.
 */
@Controller
@RequestMapping("/webSocket")
public class WebSocketController {

    @Bean//这个注解会从Spring容器拿出Bean
    public SpringWebSocketHandler infoHandler() {

        return new SpringWebSocketHandler();
    }

    @Autowired
    ContextUtils contextUtils;

    @RequestMapping("/send2All")
    public String send2All() {

        try {
            contextUtils.addQuestionToContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        infoHandler().sendMessageToUsers(new TextMessage("startCount"));
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {// 定时任务

            @Override
            public void run() {

                contextUtils.setState("start");
                contextUtils.context.setAttribute("state", "start");
                infoHandler().sendMessageToUsers(new TextMessage("startGetQuestion"));
                timer.cancel();
            }
        }, 10000, 15000);
        return "admin";
    }

    @RequestMapping("/admin")
    public String toAdmin() {
        return "admin";
    }

    @RequestMapping("/nextTurn")
    public String toNext(HttpServletRequest req) throws Exception {
        String url = "/user/doWait";
        infoHandler().sendMessageToUsers(new TextMessage(url));
        req.getSession().getServletContext().removeAttribute("scoreMap");
        contextUtils.setIndex(0);
        contextUtils.setState("notBegin");
        req.getSession().getServletContext().setAttribute("state", "notBegin");

        return "admin";
    }

}