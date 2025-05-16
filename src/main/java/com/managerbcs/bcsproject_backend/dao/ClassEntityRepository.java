package com.managerbcs.bcsproject_backend.dao;

import com.managerbcs.bcsproject_backend.entity.ClassEntity;
import com.managerbcs.bcsproject_backend.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource (path = "lop-hoc")
public interface ClassEntityRepository extends JpaRepository<ClassEntity, Integer> {
}
