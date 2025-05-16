package com.managerbcs.bcsproject_backend.controller;

import com.managerbcs.bcsproject_backend.entity.ClassLeader;
import com.managerbcs.bcsproject_backend.service.ClassLeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-leaders")
@CrossOrigin(origins = "http://localhost:3000")
public class ClassLeaderController {
    @Autowired
    private ClassLeaderService classLeaderService;

    @GetMapping
    public ResponseEntity<List<ClassLeader>> getAll() {
        return ResponseEntity.ok(classLeaderService.getAll());
    }

    @PostMapping
    public ResponseEntity<ClassLeader> create(@RequestBody ClassLeader classLeader) {
        return ResponseEntity.ok(classLeaderService.create(classLeader));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ClassLeader classLeader) {
        return classLeaderService.update(id, classLeader)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return classLeaderService.delete(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
