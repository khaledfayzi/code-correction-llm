package org.example.code_correction_llm.service;

import java.io.IOException;
import java.util.List;

import org.example.code_correction_llm.model.EvaluationResult;
import org.example.code_correction_llm.model.Submission;
import org.example.code_correction_llm.model.Task;
import org.example.code_correction_llm.repository.EvaluationResultRepository;
import org.example.code_correction_llm.repository.SubmissionRepository;
import org.example.code_correction_llm.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class CorrectionService {

    private final LLMService llmService;
    private final Evaluator evaluator;
    private final EvaluationResultRepository resultRepository; 
    private final SubmissionRepository submissionRepository;
    private final TaskRepository taskRepository;

    public CorrectionService(LLMService llmService,
                             Evaluator evaluator,
                             EvaluationResultRepository resultRepository,
                             SubmissionRepository submissionRepository,
                             TaskRepository taskRepository) {

        this.llmService = llmService;
        this.evaluator = evaluator;
        this.resultRepository = resultRepository;
        this.submissionRepository = submissionRepository;
        this.taskRepository = taskRepository;
    }

    public EvaluationResult process(Submission submission) throws IOException {

        // 🔹 Submission speichern
        Submission savedSubmission = submissionRepository.save(submission);

        // 🔹 Task holen (letzte Aufgabe)
        List<Task> tasks = taskRepository.findAll();

        if (tasks.isEmpty()) {
            throw new RuntimeException("Keine Aufgabe vorhanden");
        }

        Task task = tasks.get(tasks.size() - 1);

        // 🔥 PROMPT mit expectedOutput
        String prompt = buildPrompt(
                submission.getStudentCode(),
                task.getDescription(),
                task.getExpectedOutput()
        );

        String feedback;

        try {
            feedback = llmService.generate(prompt);
        } catch (Exception e) {
            feedback = """
                ⚠️ LLM aktuell nicht verfügbar

                Bewertung konnte nicht durchgeführt werden.
                Bitte später erneut versuchen.
                """;
        }

        // 🔹 Ergebnis auswerten
        EvaluationResult result = evaluator.evaluate(savedSubmission, feedback);
        result.setSubmission(savedSubmission);

        return resultRepository.save(result);
    }

    // 🔥 NEUER PROMPT (sehr wichtig!)
    private String buildPrompt(String code, String task, String expected) {
        if (expected == null || expected.isEmpty()) {
            expected = "Keine konkrete Lösung vorgegeben. Bewerte basierend auf allgemeiner Java-Regel.";
        }
        return """
        Du bist ein strenger Java Prüfer.

        WICHTIG:
        - Bewerte NUR basierend auf der Aufgabe
        - Vergleiche mit der erwarteten Lösung
        - Erfinde KEINE eigene Aufgabe
        - Sei streng!

        =========================
        AUFGABE:
        """ + task + """

        =========================
        ERWARTETE LÖSUNG:
        """ + expected + """

        =========================
        STUDENT CODE:
        """ + code + """

        =========================
        AUFGABE:
        1. Prüfe ob der Code die Aufgabe erfüllt
        2. Vergleiche mit der erwarteten Lösung
        3. Finde Syntaxfehler
        4. Erkläre die Fehler
        5. Gib eine korrigierte Version zurück
        6. Bewerte von 0 bis 100 (streng!)

        Am Ende schreibe:
        SCORE: <zahl>
        """;
    }
}