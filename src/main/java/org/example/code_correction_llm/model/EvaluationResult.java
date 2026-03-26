package org.example.code_correction_llm.model;

import jakarta.persistence.*;

@Entity
public class EvaluationResult{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Submission submission;

    @Column(length = 5000)
    private String feedback;
    private int score;
    private boolean passed;

    public EvaluationResult (String feedback, int score, boolean passed, Submission submission){

        this.feedback = feedback;
        this.score = score;
        this.passed = passed;
        this.submission = submission;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback){
        this.feedback = feedback;
    }

    public boolean getPassed() {
        return passed;
    }

    public void setPassed(boolean passed){
        this.passed = passed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    @Override
    public String toString() {
        return "EvaluationResult{" +
                "student=" + submission.getStudentName() +
                ", taskId=" + submission.getTaskId() +
                ", score=" + score +
                ", passed=" + passed +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
