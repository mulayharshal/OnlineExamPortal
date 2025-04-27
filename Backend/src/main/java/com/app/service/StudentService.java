package com.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.entity.Exam;
import com.app.entity.Question;
import com.app.request.SubmissionRequest;
import com.app.response.ExamResultResponse;

public interface StudentService {

	List<Exam> getAllExams();

	List<Question> getExamById(int id);
	void submitExam(Long examId, SubmissionRequest request);

	ResponseEntity<List<ExamResultResponse>> getResultsById(Long userId);

	Exam getExamData(Long examid);

}
