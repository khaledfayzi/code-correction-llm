package org.example.code_correction_llm.repository;

import org.example.code_correction_llm.model.EvaluationResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationResultRepository extends JpaRepository<EvaluationResult, Long> {
}