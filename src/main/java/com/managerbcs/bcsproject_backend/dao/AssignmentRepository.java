package com.managerbcs.bcsproject_backend.dao;

import com.managerbcs.bcsproject_backend.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource (path = "phan-cong")
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
}
