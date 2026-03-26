package org.example.code_correction_llm.service;

import org.example.code_correction_llm.model.Submission;
import org.example.code_correction_llm.model.EvaluationResult;
import org.springframework.stereotype.Component;

@Component
public class Evaluator {
    private static final int PASS_THRESHOLD = 50;


    public EvaluationResult evaluate(Submission submission, String feedback) {

        int score = extractScore(feedback);
        boolean passed = score >= PASS_THRESHOLD;

        return new EvaluationResult(feedback, score, passed, submission);

    }

    private int extractScore(String feedback) {
        try {
            String upper = feedback.toUpperCase();
            int index = upper.indexOf("SCORE:");

            if(index == -1) {
                throw new RuntimeException("Kein SCORE gefunden!");

            }
            String scorePart = upper.substring(index + 6).trim();

            String number = scorePart.split("\\D+")[0];

            return Integer.parseInt(number);
        }catch (Exception e) {
            System.out.println("Fehler beim Score extrahieren: " + e.getMessage());
            return -1;
        }
    }


}


