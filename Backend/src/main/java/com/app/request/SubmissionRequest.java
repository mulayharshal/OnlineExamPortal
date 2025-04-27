package com.app.request;


import java.util.List;

public class SubmissionRequest {
    private Long userId;
    private List<AnswerRequest> answers;

    // getters and setters

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<AnswerRequest> getAnswers() { return answers; }
    public void setAnswers(List<AnswerRequest> answers) { this.answers = answers; }
}
