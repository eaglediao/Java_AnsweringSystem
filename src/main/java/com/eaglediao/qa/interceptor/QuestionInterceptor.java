package com.eaglediao.qa.interceptor;

import com.eaglediao.qa.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class QuestionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getSession().getAttribute("user") == null) {
            request.getRequestDispatcher("/user/doLogin").forward(request, response);
            return false;
        }
        Object state = request.getSession().getServletContext().getAttribute("state");
        if (state == null) {
            request.getRequestDispatcher("/user/doWait").forward(request, response);
            return false;
        } else if ("notBegin".equals(state)) {
            response.setHeader("refresh", "5;url=/question/getQuestion");
            return false;
        } else if ("closed".equals(state)) {
            response.setHeader("refresh", "5;url=/question/getResult");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}