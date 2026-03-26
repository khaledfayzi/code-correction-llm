package org.example.code_correction_llm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String expectedOutput;

    public Task(){
    }
    public Task(String description, String expectedOutput) {
        this.description = description;
        this.expectedOutput = expectedOutput;
    }

    // Getter und Setter

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getExpectedOutput(){
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput){
        this.expectedOutput = expectedOutput;
    }

}
