package com.managerbcs.bcsproject_backend.service;

import com.managerbcs.bcsproject_backend.dao.TaskRepository;
import com.managerbcs.bcsproject_backend.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> updateTask(Integer id, Task data) {
        return taskRepository.findById(id).map(existing -> {
            existing.setClassId(data.getClassId());
            existing.setAssignedBy(data.getAssignedBy());
            existing.setTitle(data.getTitle());
            existing.setDescription(data.getDescription());
            existing.setDueDate(data.getDueDate());
            existing.setPriority(data.getPriority());
            existing.setAttachment(data.getAttachment());
            return taskRepository.save(existing);
        });
    }

    public boolean deleteTask(Integer id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
