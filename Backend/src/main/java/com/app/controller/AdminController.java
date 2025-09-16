package com.app.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = {"http://localhost:4200", "https://onlineexamportal.up.railway.app"}, allowCredentials = "true")
public class AdminController {

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
    
    @Autowired
    private AdminService adminService;

    @GetMapping("/exams/{userId}")
    public ResponseEntity<List<Exam>> getExamsAdmin(@PathVariable Long userId) {
    	List<Exam> response=adminService.getExamsAdmin(userId);
        return new ResponseEntity<List<Exam>>(response,HttpStatus.OK);
    }
    
    @PostMapping("/create-exam/{userId}")
    public ResponseEntity<Response> createExam(@PathVariable Long userId, @RequestBody Exam exam) {
    	
    	Response response=adminService.createExam(userId, exam);
        return  new ResponseEntity<Response>(response,HttpStatus.CREATED);
    }

    @PostMapping("/add-question/{examId}")
    public ResponseEntity<?> addQuestion(@PathVariable Long examId, @RequestBody Question question) {
    	
    	Response response=adminService.addQuestion(examId , question);
        return new ResponseEntity<Response>(response,HttpStatus.CREATED);
    }

    
    @GetMapping("/results/{examId}")
    public ResponseEntity<List<ExamResultResponse>> getResults(@PathVariable Long examId) {
    	
    	return adminService.getResults(examId);
    }
    
    
    
    @PutMapping("/update-question/{questionId}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long questionId, @RequestBody Question updatedQuestion) {
    	
    	Response response=adminService.updateQuestion(questionId, updatedQuestion);
    	return new ResponseEntity<Response>(response,HttpStatus.CREATED);
    }
    
    @DeleteMapping("/delete-question/{questionId}")
    @Transactional
    public Response deleteQuestion(@PathVariable Long questionId) {
    	
    	return adminService.deleteQuestion(questionId);
        
        
    }
    
    @PutMapping("/update-exam/{id}")
    public Response updateExam(@PathVariable Long id, @RequestBody Exam updatedExam) {
    	Response response= adminService.updateExam(id, updatedExam);
       return response;
    }
    
    
    @GetMapping("/get-exam/{id}")
    public Exam getExamById(@PathVariable Long id) {
        return examRepository.findById(id)
                .orElse(new Exam()); // Return empty Exam if not found
    }
    
    @GetMapping("/get-question/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionRepository.findById(id)
                .orElse(new Question()); // Return empty Question if not found
    }





}
