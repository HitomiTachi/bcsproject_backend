package com.managerbcs.bcsproject_backend.service;

import com.managerbcs.bcsproject_backend.dao.ClassLeaderEvaluationRepository;
import com.managerbcs.bcsproject_backend.entity.ClassLeaderEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassLeaderEvaluationService {
    @Autowired
    private ClassLeaderEvaluationRepository evaluationRepository;

    public List<ClassLeaderEvaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }
}
