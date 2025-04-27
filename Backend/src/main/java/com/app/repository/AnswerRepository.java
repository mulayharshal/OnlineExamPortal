package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

	void deleteByQuestionId(Long questionId);
}
