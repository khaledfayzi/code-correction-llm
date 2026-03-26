package org.example.code_correction_llm.repository;

import org.example.code_correction_llm.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}