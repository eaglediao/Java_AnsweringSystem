package com.eaglediao.qa.domain;


import com.eaglediao.qa.util.QAConstants;

import java.util.Arrays;
import java.util.Map;

public class QuestionInfo extends Question {

    private String result;
    private String userAnswer;
    private Boolean isLast=false;
    private Map<String, String> options;
    private Integer[] qaStatus;

    public QuestionInfo() {
        qaStatus = new Integer[QAConstants.QUESTION_NUM];
        Arrays.fill(qaStatus, 2);

    }

    public Integer[] getQaStatus() {
        return qaStatus;
    }

    public void setQaStatus(Integer[] qaStatus) {
        this.qaStatus = qaStatus;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public QuestionInfo setOptions(Map<String, String> options) {
        this.options = options;
        return this;
    }

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean last) {
        isLast = last;
    }

    public String getResult() {
        return result;
    }

    public QuestionInfo setResult(String result) {
        this.result = result;
        return this;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
