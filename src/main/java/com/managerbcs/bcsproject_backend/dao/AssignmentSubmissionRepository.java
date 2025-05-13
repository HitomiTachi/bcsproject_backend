package com.managerbcs.bcsproject_backend.dao;

import com.managerbcs.bcsproject_backend.entity.AssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource (path = "nop-bai-tap")
public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Integer> {
}
