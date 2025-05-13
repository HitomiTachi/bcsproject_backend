package com.managerbcs.bcsproject_backend.dao;

import com.managerbcs.bcsproject_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "nguoi-dung")
public interface UserRepository extends JpaRepository<User, Integer> {
}
