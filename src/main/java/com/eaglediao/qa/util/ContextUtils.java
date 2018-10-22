package com.eaglediao.qa.util;

import com.eaglediao.qa.domain.Question;
import com.eaglediao.qa.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class ContextUtils implements ServletContextAware {

    private static int index = 0;
    private static String state = "notBegin";

    public ServletContext context;

    @Autowired
    public QuestionService questionService;


    public static int getIndex() {
        return index;
    }

    public void addQuestionToContext() throws Exception {

        List<Question> questionList = questionService.getQuestionList(QAConstants.QUESTION_NUM);
        context.setAttribute("questionList", questionList);
        context.setAttribute("state", state);
        System.out.println("add question");
        time();
    }

    public void time() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                if (index < QAConstants.QUESTION_NUM - 1) {
                    index++;
                } else {
                    state = "closed";
                    context.setAttribute("state", state);
                    timer.cancel();
                }
            }
        }, 25000, 15000);
    }


    public Question getQuestionFromContext() {

        List<Question> questionList = (List<Question>) context.getAttribute("questionList");
        return questionList.get(index);

    }

    public static void setIndex(int index) {
        ContextUtils.index = index;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }

    public static String getState() {
        return state;
    }

    public static void setState(String state) {
        ContextUtils.state = state;

    }
}