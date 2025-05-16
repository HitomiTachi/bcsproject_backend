package com.managerbcs.bcsproject_backend.controller;

import com.managerbcs.bcsproject_backend.entity.ClassEntity;
import com.managerbcs.bcsproject_backend.service.ClassEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "http://localhost:3000")
public class ClassEntityController {
    @Autowired
    private ClassEntityService classService;

    @GetMapping
    public ResponseEntity<List<ClassEntity>> getAll() {
        return ResponseEntity.ok(classService.getAllClasses());
    }

    @PostMapping
    public ResponseEntity<ClassEntity> create(@RequestBody ClassEntity classEntity) {
        return ResponseEntity.ok(classService.createClass(classEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ClassEntity classEntity) {
        return classService.updateClass(id, classEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return classService.deleteClass(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
