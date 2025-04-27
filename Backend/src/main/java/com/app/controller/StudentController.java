package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.entity.Answer;
import com.app.entity.Exam;
import com.app.entity.Question;
import com.app.entity.Submission;
import com.app.repository.AnswerRepository;
import com.app.repository.ExamRepository;
import com.app.repository.QuestionRepository;
import com.app.repository.SubmissionRepository;
import com.app.request.SubmissionRequest;
import com.app.response.ExamResultResponse;
import com.app.service.StudentService;
import com.app.service.SubmissionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

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
    
    @Autowired
    private StudentService  studentService;

    @GetMapping("/exams")
    public ResponseEntity<List<Exam>> getExams() {
//    	List<Exam> response=examRepository.findAll();
    	List<Exam> response=studentService.getAllExams();
    	return new ResponseEntity<List<Exam>>(response,HttpStatus.OK);
    }

    
    @GetMapping("/exam/{id}")
    public ResponseEntity<List<Question>> getQuestion(@PathVariable int id) {
//    	List<Question> response=questionRepository.findAllByexam_id(id);
    	List<Question> response=studentService.getExamById(id);
    	return new ResponseEntity<List<Question>>(response,HttpStatus.OK);
    }
    
    
    @PostMapping("/submit-exam/{examId}")
    public ResponseEntity<Map<String, String>> submitExam(
            @PathVariable Long examId,
            @RequestBody SubmissionRequest request) {

    	
        // Submit the exam
        studentService.submitExam(examId, request);

        // Return a JSON response with the success message
        Map<String, String> response = new HashMap<>();
        response.put("message", "Exam submitted successfully!");
        
        return ResponseEntity.ok(response);
    }


    
    
    // Get all the results for the student (score and exam details)
    @GetMapping("/results/{userId}")
    public ResponseEntity<List<ExamResultResponse>> getResults(@PathVariable Long userId) {
//  
        return studentService.getResultsById(userId);
    }
    
    
    @GetMapping("/exam/data/{examid}")
    public ResponseEntity<Exam> getExamsInfo(@PathVariable Long examid) {
    	Exam response=studentService.getExamData(examid);
    	return new ResponseEntity<Exam>(response,HttpStatus.OK);
    }
    
    
    
    
    




}

