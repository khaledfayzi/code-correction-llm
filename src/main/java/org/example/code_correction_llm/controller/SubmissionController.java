package org.example.code_correction_llm.controller;
import org.example.code_correction_llm.model.Submission;
import org.example.code_correction_llm.model.EvaluationResult;
import org.example.code_correction_llm.service.CorrectionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//Das ist eine API-Klasse
@RestController
//Alle URLs starten mit /api
@RequestMapping("/api")
public class SubmissionController {
    private final CorrectionService correctionService;

    public SubmissionController(CorrectionService correctionService) {
        this.correctionService = correctionService;
    }

    @PostMapping("/submit")
    public EvaluationResult submit(@RequestBody Submission submission) throws IOException {

        return correctionService.process(submission);
    }
}
