package com.app.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.entity.Answer;
import com.app.entity.Exam;
import com.app.entity.Question;
import com.app.entity.Submission;
import com.app.repository.AnswerRepository;
import com.app.repository.ExamRepository;
import com.app.repository.QuestionRepository;
import com.app.repository.SubmissionRepository;
import com.app.request.AnswerRequest;
import com.app.request.SubmissionRequest;
import com.app.response.ExamResultResponse;
import com.app.service.StudentService;
import com.app.service.SubmissionService;

@Service
public class StudentServiceImpl implements StudentService{

	
	@Autowired
    private ExamRepository examRepository;

    @Autowired
    private SubmissionRepository submissionRepository;
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private AnswerRepository  answerRepository;
    
    
    @Autowired
    private SubmissionService submissionService;


	@Override
	public List<Exam> getAllExams() {
		// TODO Auto-generated method stub
		List<Exam> response=examRepository.findAll();
		return response;
	}


	@Override
	public List<Question> getExamById(int id) {
		List<Question> response=questionRepository.findAllByexam_id(id);
		return response;
	}
	
	@Override
	public void submitExam(Long examId, SubmissionRequest request) {
	    Exam exam = examRepository.findById(examId).orElseThrow();
	    int score = 0;

	    Submission submission = new Submission();
	    submission.setExam(exam);
	    submission.setUserId(request.getUserId());
	    submission.setSubmissionTime(LocalDateTime.now());

	    List<Answer> answerList = new ArrayList<>();

	    // Iterate through each answer provided by the student
	    for (AnswerRequest a : request.getAnswers()) {
	        // Fetch the question from the database
	        Question question = questionRepository.findById(a.getQuestionId()).orElseThrow();

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
	    submissionRepository.save(submission);
	}


	@Override
	public ResponseEntity<List<ExamResultResponse>> getResultsById(Long userId) {
		List<Submission> submissions = submissionRepository.findByUserId(userId);

        if (submissions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // If no results found
        }

        // Create a list of ExamResultResponse to hold the student's results
        List<ExamResultResponse> responseList = new ArrayList<>();

        // Populate the response list with each submission's exam details and score
        for (Submission submission : submissions) {
            ExamResultResponse response = new ExamResultResponse();
            response.setExamName(submission.getExam().getTitle()); // Exam name
            response.setTotalMarks(submission.getExam().getTotalMarks()); // Total number of questions
            response.setScore(submission.getScore()); // User's score
            response.setSubmissionTime(submission.getSubmissionTime()); // Set submission time
            responseList.add(response);
        }

        return ResponseEntity.ok(responseList); 
	}


	@Override
	public Exam getExamData(Long examid) {
		Exam response=examRepository.findById(examid).get();
		return response;
	}

	
}
