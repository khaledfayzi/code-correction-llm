package service;

import model.Task;
import model.Submission;
import model.EvaluationResult;

import java.util.Objects;

public class Evaluator {

    public EvaluationResult evaluate(Submission submission, Task task){

        String expectedOutput = task.getExpectedOutput();
        String studentCode = submission.getStudentCode();

        String feedback = "Correct";
        int score = 100;
        boolean pass = true;

        String feedback1="Not Correct";
        int score1 = 0;
        boolean pass1 = false;


        if (Objects.equals(expectedOutput, studentCode)){
            return new EvaluationResult(feedback,score,pass,submission);
        }
        else {

            return new EvaluationResult(feedback1, score1, pass1, submission);

        }


    }
}


