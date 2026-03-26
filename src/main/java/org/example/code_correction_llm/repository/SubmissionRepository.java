package org.example.code_correction_llm.repository;

import org.example.code_correction_llm.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}