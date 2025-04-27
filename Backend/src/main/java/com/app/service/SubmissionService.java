package com.app.service;

import com.app.request.SubmissionRequest;

public interface SubmissionService {

	void submitExam(Long examId, SubmissionRequest request);

}
