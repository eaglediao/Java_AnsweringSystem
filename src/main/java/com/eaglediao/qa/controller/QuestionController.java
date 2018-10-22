package com.eaglediao.qa.controller;

import com.eaglediao.qa.domain.Question;
import com.eaglediao.qa.domain.QuestionInfo;
import com.eaglediao.qa.domain.User;
import com.eaglediao.qa.service.QuestionService;
import com.eaglediao.qa.service.UserService;
import com.eaglediao.qa.util.QAConstants;
import com.eaglediao.qa.util.ContextUtils;
import com.eaglediao.qa.util.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

@Controller
@RequestMapping("/question")
public class QuestionController {
    private static Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    public QuestionService questionService;
    @Autowired
    public UserService userService;

    @Autowired
    public ContextUtils contextUtils;

    @RequestMapping(value = "/getQuestion")
    public ModelAndView getQuestion(HttpServletRequest request) {

        Question question = null;

        ModelAndView modelAndView = new ModelAndView("qa");
        HttpSession session = request.getSession();
        Integer[] qaStatus = session.getAttribute("qaStatus") == null ? new QuestionInfo().getQaStatus() : (Integer[]) session
                .getAttribute("qaStatus");

        for (int i = 0; i < ContextUtils.getIndex(); i++) {
            if (qaStatus[i] == 2) {
                qaStatus[i] = 1;
            }
        }
        session.setAttribute("qaStatus", qaStatus);
        try {
            question = contextUtils.getQuestionFromContext();
            if (question == null) {
                question = questionService.getQuestion();
            }
            session.setAttribute("answer", question.getAnswer());
            session.setAttribute("score", question.getScore());
            Map map = JsonUtils.json2Map(question.getOption());

            modelAndView.addObject("content", question.getContent()).addObject("options", map).addObject("qaStatus", qaStatus);

            if (ContextUtils.getState().equals("closed")) {

                session.setAttribute("qaStatus", null);
                ((User) request.getSession().getAttribute("user")).setStatus("on");
                modelAndView.setViewName("forward:/question/generateResult");
            }
        } catch (Exception e) {
            log.error("getQuestion Exception", e);
            modelAndView.addObject("message", "unexpected err");

        }
        return modelAndView;
    }

    @RequestMapping("/judge")

    public @ResponseBody
    QuestionInfo judge(HttpServletRequest request, HttpServletResponse response) {

        try {
            String answer = request.getParameter("answer");
            HttpSession session = request.getSession();
            String rightAnswer = (String) (session.getAttribute("answer"));
            User user = (User) session.getAttribute("user");

            QuestionInfo questionInfo = new QuestionInfo();
            Integer[] qaStatus = session.getAttribute("qaStatus") == null ? questionInfo.getQaStatus() : (Integer[]) session
                    .getAttribute("qaStatus");

            if (StringUtils.equals(rightAnswer, answer)) {
//                    if (user.getStatus() != "finished") {
                user.setScore(user.getScore() + (Integer) (session.getAttribute("score")) + 0);
                session.setAttribute("user", user);
//                    }
                questionInfo.setResult("correct");
                qaStatus[ContextUtils.getIndex()] = 0;
            } else {
                user.setStatus("finished");
                qaStatus[ContextUtils.getIndex()] = 1;
            }

            if (ContextUtils.getIndex() == QAConstants.QUESTION_NUM) {

                questionInfo.setLast(true);
            }
            questionInfo.setAnswer(rightAnswer);
            questionInfo.setUserAnswer(answer);
            questionInfo.setQaStatus(qaStatus);
            session.setAttribute("qaStatus", qaStatus);
            return questionInfo;

        } catch (Exception e) {
            log.error("judge Exception", e);
            return null;
        }
    }

    @RequestMapping("/generateResult")
    public void generateResult(HttpServletRequest request, HttpServletResponse resp) {

        ServletContext context = request.getSession().getServletContext();
        Comparator<Integer> sortByName = (Integer s1, Integer s2) -> (s2.compareTo(s1));

        TreeMap<Integer, Set<String>> scoreMap = context.getAttribute("scoreMap") == null ? new TreeMap<>(sortByName) :
                (TreeMap) context.getAttribute("scoreMap");
        User user = (User) request.getSession().getAttribute("user");

        Integer score = user.getScore();
        if (scoreMap.get(score) != null) {
            scoreMap.get(score).add(user.getEmployeeId());
        } else {
            Set<String> idSet = new HashSet<>();
            if (scoreMap.size() < QAConstants.RESULT_NUM) {

                idSet.add(user.getEmployeeId());
                scoreMap.put(score, idSet);

            } else {
                if (scoreMap.lastKey() < score) {
                    scoreMap.remove(scoreMap.lastKey());
                    idSet.add(user.getEmployeeId());
                    scoreMap.put(score, idSet);
                }
            }
        }
        context.setAttribute("scoreMap", scoreMap);

        resp.setHeader("refresh", "5;url=/question/getResult");
    }

    @RequestMapping("/getResult")
    public ModelAndView getResult(HttpServletRequest req, HttpServletResponse resp) {

        TreeMap<Integer, Set<String>> scoreMap = (TreeMap) req.getSession().getServletContext().getAttribute("scoreMap");

        return new ModelAndView("result", "scoreMap", scoreMap);
    }

}


