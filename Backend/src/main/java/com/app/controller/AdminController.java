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
import com.app.response.ResponseMessage;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true") 
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

    @GetMapping("/exams/{userId}")
    public List<Exam> getExams(@PathVariable Long userId) {
        return examRepository.findByUserId(userId);
    }
    
    @PostMapping("/create-exam/{userId}")
    public ResponseEntity<Exam> createExam(@PathVariable Long userId, @RequestBody Exam exam) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        exam.setUser(user);
        exam.setCreatedTime(LocalDateTime.now());
        Exam savedExam = examRepository.save(exam);
        return ResponseEntity.ok(savedExam);
    }

    @PostMapping("/add-question/{examId}")
    public ResponseEntity<?> addQuestion(@PathVariable Long examId, @RequestBody Question question) {
        // Check if exam exists
        Exam exam = examRepository.findById(examId).get();
                        
        // Set the exam for the question
        question.setExam(exam);
        
        // Save the question to the database
        questionRepository.save(question);

        // Return a JSON response with success message
        return ResponseEntity.ok(new ResponseMessage("Question added successfully"));
    }

    
    @GetMapping("/results/{examId}")
    public ResponseEntity<List<ExamResultResponse>> getResults(@PathVariable Long examId) {
    	System.out.println("User ID received: " + examId);

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
    
    
    
    @PutMapping("/update-question/{questionId}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long questionId, @RequestBody Question updatedQuestion) {
        // Find the existing question
        Question existingQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + questionId));

        // Update the fields
        existingQuestion.setContent(updatedQuestion.getContent());
        existingQuestion.setOptionA(updatedQuestion.getOptionA());
        existingQuestion.setOptionB(updatedQuestion.getOptionB());
        existingQuestion.setOptionC(updatedQuestion.getOptionC());
        existingQuestion.setOptionD(updatedQuestion.getOptionD());
        existingQuestion.setCorrectAnswer(updatedQuestion.getCorrectAnswer());

        // Save the updated question
        questionRepository.save(existingQuestion);

        // Return a success message
        return ResponseEntity.ok(new ResponseMessage("Question updated successfully"));
    }
    
    @DeleteMapping("/delete-question/{questionId}")
    @Transactional
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId) {
        // First delete all answers related to this question
        answerRepository.deleteByQuestionId(questionId);

        // Then delete the question
        questionRepository.deleteById(questionId);

        return ResponseEntity.ok("{\"message\": \"Question deleted successfully\"}");
    }
    
    @PutMapping("/update-exam/{id}")
    public Exam updateExam(@PathVariable Long id, @RequestBody Exam updatedExam) {
        Optional<Exam> optionalExam = examRepository.findById(id);

        if (optionalExam.isPresent()) {
            Exam existingExam = optionalExam.get();
            existingExam.setTitle(updatedExam.getTitle());
            existingExam.setDescription(updatedExam.getDescription());
            existingExam.setDuration(updatedExam.getDuration());

            return examRepository.save(existingExam);
        } else {
            // Return empty Exam object if not found
            return new Exam();
        }
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
