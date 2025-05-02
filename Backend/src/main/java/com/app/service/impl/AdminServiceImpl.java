package com.app.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.entity.Exam;
import com.app.entity.Question;
import com.app.entity.Submission;
import com.app.entity.User;
import com.app.repository.AnswerRepository;
import com.app.repository.ExamRepository;
import com.app.repository.QuestionRepository;
import com.app.repository.SubmissionRepository;
import com.app.repository.UserRepository;
import com.app.response.ExamResultResponse;
import com.app.response.Response;
import com.app.response.ResponseMessage;
import com.app.service.AdminService;
import com.app.util.Status;

@Service
public class AdminServiceImpl  implements AdminService{
	 @Autowired
	    private ExamRepository examRepository;

	    @Autowired
	    private QuestionRepository questionRepository;
	    
	    @Autowired 
	    private UserRepository userRepository;
	    
	    @Autowired
	    private AnswerRepository answerRepository;
	    
	    @Autowired
	    private SubmissionRepository submissionRepository;

	@Override
	public List<Exam> getExamsAdmin(Long userId) {
		// TODO Auto-generated method stub
		List<Exam> response=examRepository.findByUserId(userId);
		return response;
	}

	@Override
	public Response createExam(Long userId, Exam exam) {
    	Response response=new Response();
    	
    	System.out.println(userId +"    "+ exam);
    	try {
    		
        User user = userRepository.findById(userId).get();
        System.out.println(user);
      
        	exam.setUser(user);
            exam.setCreatedTime(LocalDateTime.now());
            Exam savedExam = examRepository.save(exam);
            response.setRes(savedExam);
            response.setStatus(Status.SUCCESS);
        }catch (Exception e) {
		
			response.setRes("Somthing Wrong Try Again !");
			response.setStatus(Status.FAIL);
		}
        
        return response;
	}

	@Override
	public Response addQuestion(Long examId, Question question) {
		Response response=new Response();
		
		try {
			Exam exam = examRepository.findById(examId).get();
	        question.setExam(exam);
	        questionRepository.save(question);
	        response.setRes("Question added successfully");
			response.setStatus(Status.SUCCESS);
		} catch (Exception e) {
			response.setRes("Somthing Wrong Try Again !");
			response.setStatus(Status.FAIL);
		}
	        return response;
	}

	@Override
	public ResponseEntity<List<ExamResultResponse>> getResults(Long examId) {
		List<Submission> submissions = submissionRepository.findByExamId(examId);

        if (submissions.isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>()); // Return empty list instead of 404
        }

        List<ExamResultResponse> responseList = new ArrayList<>();

        for (Submission submission : submissions) {
            ExamResultResponse response = new ExamResultResponse();
            response.setExamName(submission.getExam().getTitle());
            response.setTotalMarks(submission.getExam().getTotalMarks());
            response.setScore(submission.getScore());
            response.setSubmissionTime(submission.getSubmissionTime());

            Long userId = submission.getUserId();
            userRepository.findById(userId).ifPresent(user -> response.setUserName(user.getName()));

            responseList.add(response);
        }

        return ResponseEntity.ok(responseList);
	}

	@Override
	public Response updateQuestion(Long questionId, Question updatedQuestion) {
		Response response= new Response();
		
		try {
			Question existingQuestion = questionRepository.findById(questionId).get();
	        existingQuestion.setContent(updatedQuestion.getContent());
	        existingQuestion.setOptionA(updatedQuestion.getOptionA());
	        existingQuestion.setOptionB(updatedQuestion.getOptionB());
	        existingQuestion.setOptionC(updatedQuestion.getOptionC());
	        existingQuestion.setOptionD(updatedQuestion.getOptionD());
	        existingQuestion.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
	        questionRepository.save(existingQuestion);
	        response.setRes("Question updated successfully");
	        response.setStatus(Status.SUCCESS);
		} catch (Exception e) {
			response.setRes("Somthing Wrong Try Again !");
			response.setStatus(Status.FAIL);
		}
        

        // Return a success message
        return response;

	}

	@Override
	public Response deleteQuestion(Long questionId) {
		Response response= new Response();
		try {
			answerRepository.deleteByQuestionId(questionId);
	        questionRepository.deleteById(questionId);
	        response.setRes("Question deleted successfully");
	        response.setStatus(Status.SUCCESS);
		} catch (Exception e) {
			response.setRes("Somthing Wrong Try Again !");
			response.setStatus(Status.FAIL);
		}
		return response;
	}

	@Override
	public Response updateExam(Long id, Exam updatedExam) {
		Response response= new Response();
		
		Optional<Exam> optionalExam = examRepository.findById(id);

        if (optionalExam.isPresent()) {
            Exam existingExam = optionalExam.get();
            existingExam.setTitle(updatedExam.getTitle());
            existingExam.setDescription(updatedExam.getDescription());
            existingExam.setDuration(updatedExam.getDuration());

            Exam exam= examRepository.save(existingExam);
            response.setRes(exam);
            response.setStatus(Status.SUCCESS);
        } else {
            response.setRes(new Exam());
            response.setStatus(Status.FAIL);
        }
        return response;
	}

}
