package org.example.code_correction_llm.service;

import org.example.code_correction_llm.model.EvaluationResult;
import org.example.code_correction_llm.model.Submission;
import org.example.code_correction_llm.repository.EvaluationResultRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CorrectionService {

    private final LLMService llmService;
    private final Evaluator evaluator;
    private final EvaluationResultRepository resultRepository; // 🔥 NEU

    public CorrectionService(LLMService llmService,
                             Evaluator evaluator,
                             EvaluationResultRepository resultRepository) {
        this.llmService = llmService;
        this.evaluator = evaluator;
        this.resultRepository = resultRepository;
    }

    public EvaluationResult process(Submission submission) throws IOException {

        String prompt = buildPrompt(submission.getStudentCode());
        String feedback = llmService.generate(prompt);

        EvaluationResult result = evaluator.evaluate(submission, feedback);

        return resultRepository.save(result); // 🔥 SPEICHERN
    }

    private String buildPrompt(String code) {
        return """
            Analysiere den folgenden Java-Code.

            1. Finde alle Syntaxfehler
            2. Erkläre die Fehler
            3. Gib eine korrigierte Version zurück
            4. Bewerte den Code von 0 bis 100

            Am Ende schreibe:
            SCORE: <zahl>

            Code:
            """ + code;
    }
}