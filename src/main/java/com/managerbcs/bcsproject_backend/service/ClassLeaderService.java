package com.managerbcs.bcsproject_backend.service;

import com.managerbcs.bcsproject_backend.dao.ClassLeaderRepository;
import com.managerbcs.bcsproject_backend.entity.ClassLeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassLeaderService {
    @Autowired
    private ClassLeaderRepository classLeaderRepository;

    public List<ClassLeader> getAll() {
        return classLeaderRepository.findAll();
    }

    public ClassLeader create(ClassLeader classLeader) {
        return classLeaderRepository.save(classLeader);
    }

    public Optional<ClassLeader> update(Integer id, ClassLeader data) {
        return classLeaderRepository.findById(id).map(existing -> {
            existing.setClassId(data.getClassId());
            existing.setUserId(data.getUserId());
            existing.setPosition(data.getPosition());
            existing.setStartDate(data.getStartDate());
            existing.setEndDate(data.getEndDate());
            return classLeaderRepository.save(existing);
        });
    }

    public boolean delete(Integer id) {
        if (classLeaderRepository.existsById(id)) {
            classLeaderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
