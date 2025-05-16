package com.managerbcs.bcsproject_backend.controller;

import com.managerbcs.bcsproject_backend.entity.ClassLeaderEvaluation;
import com.managerbcs.bcsproject_backend.service.ClassLeaderEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/class-leader-evaluations")
@CrossOrigin(origins = "http://localhost:3000")
public class ClassLeaderEvaluationController {
    @Autowired
    private ClassLeaderEvaluationService evaluationService;

    @GetMapping
    public ResponseEntity<List<ClassLeaderEvaluation>> getAll() {
        return ResponseEntity.ok(evaluationService.getAllEvaluations());
    }
}
