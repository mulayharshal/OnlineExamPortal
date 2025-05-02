package com.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.entity.Exam;
import com.app.entity.Question;
import com.app.response.ExamResultResponse;
import com.app.response.Response;

public interface AdminService {

	List<Exam> getExamsAdmin(Long userId);

	Response createExam(Long userId, Exam exam);

	Response addQuestion(Long examId, Question question);

	ResponseEntity<List<ExamResultResponse>> getResults(Long examId);

	Response updateQuestion(Long questionId, Question updatedQuestion);

	Response deleteQuestion(Long questionId);

	Response updateExam(Long id, Exam updatedExam);

}
