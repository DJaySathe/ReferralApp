package com.dssathe.referralsapp;

/**
 * Created by dsathe on 10/21/17.
 */

public class Reply {
    String status;
    double[] answer;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double[] getAnswer() {
        return answer;
    }

    public void setAnswer(double[] answer) {
        this.answer = answer;
    }
}
