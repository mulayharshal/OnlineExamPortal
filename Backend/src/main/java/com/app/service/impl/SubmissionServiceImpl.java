package com.app.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Answer;
import com.app.entity.Exam;
import com.app.entity.Question;
import com.app.entity.Submission;
import com.app.repository.ExamRepository;
import com.app.repository.QuestionRepository;
import com.app.repository.SubmissionRepository;
import com.app.request.AnswerRequest;
import com.app.request.SubmissionRequest;
import com.app.service.SubmissionService;

@Service
public class SubmissionServiceImpl implements SubmissionService{
	
	@Autowired
	private ExamRepository examRepo;
	
	@Autowired
	private QuestionRepository questionRepo;
	
	@Autowired
	private SubmissionRepository submissionRepo;

	@Override
	public void submitExam(Long examId, SubmissionRequest request) {
	    Exam exam = examRepo.findById(examId).orElseThrow();
	    int score = 0;

	    Submission submission = new Submission();
	    submission.setExam(exam);
	    submission.setUserId(request.getUserId());
	    submission.setSubmissionTime(LocalDateTime.now());

	    List<Answer> answerList = new ArrayList<>();

	    // Iterate through each answer provided by the student
	    for (AnswerRequest a : request.getAnswers()) {
	        // Fetch the question from the database
	        Question question = questionRepo.findById(a.getQuestionId()).orElseThrow();

	        Answer answer = new Answer();
	        answer.setQuestion(question);
	        answer.setSelectedOption(a.getSelectedOption());
	        answer.setSubmission(submission);

	        

	        System.out.println(a.getSelectedOption()+" and Coreect is "+question.getCorrectAnswer());
	        // Check if the selected option matches the correct answer (ignoring case and whitespace)
	        if (a.getSelectedOption() != null 
	            && a.getSelectedOption().trim().equalsIgnoreCase(question.getCorrectAnswer().trim())) {
	        	
	            score++;  // Increment score if the answer is correct
	        }

	        // Add the answer to the answer list
	        answerList.add(answer);
	    }

	    // Save the submission with the calculated score
	    submission.setAnswers(answerList);
	    submission.setScore(score);

	    // Log the final score
	    System.out.println("Final Score for Submission: " + score);

	    // Save the submission to the database
	    submissionRepo.save(submission);
	}





}
