package org.example.code_correction_llm.model;

import jakarta.persistence.*;

@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;

    @Column(length = 5000)
    private String studentCode;

    private int taskId;

    public Submission() {}

    public Submission(String studentCode, String studentName, int taskId){
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.taskId = taskId;
    }

    // Getter & Setter

    public Long getId() {
        return id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getTaskId() {
        return taskId;
    }
}