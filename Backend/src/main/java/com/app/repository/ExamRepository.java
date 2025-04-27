package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
	List<Exam> findByUserId(Long userId); 
}
