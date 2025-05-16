package com.managerbcs.bcsproject_backend.dao;

import com.managerbcs.bcsproject_backend.entity.TaskAssignment;
import com.managerbcs.bcsproject_backend.entity.TaskAssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "phan-cong-nhiem-vu")
public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, TaskAssignmentId> {
}
