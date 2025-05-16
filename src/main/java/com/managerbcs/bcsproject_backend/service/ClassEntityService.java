package com.managerbcs.bcsproject_backend.service;

import com.managerbcs.bcsproject_backend.dao.ClassEntityRepository;
import com.managerbcs.bcsproject_backend.entity.ClassEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassEntityService {
    @Autowired
    private ClassEntityRepository classRepository;

    public List<ClassEntity> getAllClasses() {
        return classRepository.findAll();
    }

    public ClassEntity createClass(ClassEntity classEntity) {
        return classRepository.save(classEntity);
    }

    public Optional<ClassEntity> updateClass(Integer id, ClassEntity updated) {
        return classRepository.findById(id).map(existing -> {
            existing.setClassCode(updated.getClassCode());
            existing.setClassName(updated.getClassName());
            existing.setMajor(updated.getMajor());
            existing.setCourse(updated.getCourse());
            existing.setLecturer(updated.getLecturer());
            return classRepository.save(existing);
        });
    }

    public boolean deleteClass(Integer id) {
        if (classRepository.existsById(id)) {
            classRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
