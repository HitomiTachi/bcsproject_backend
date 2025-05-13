package com.managerbcs.bcsproject_backend.dao;

import com.managerbcs.bcsproject_backend.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "Nhom")
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
