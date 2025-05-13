package com.managerbcs.bcsproject_backend.dao;

import com.managerbcs.bcsproject_backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "du-an")
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
