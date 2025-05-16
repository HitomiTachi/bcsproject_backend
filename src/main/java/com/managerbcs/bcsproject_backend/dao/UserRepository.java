package com.managerbcs.bcsproject_backend.dao;

import com.managerbcs.bcsproject_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource(path = "nguoi-dung")
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByStudentIdAndPasswordAndStatus(String studentId, String password, Integer status);

    Optional<User> findByUserIdAndPasswordAndStatus(Integer userId, String password, Integer status);

    Optional<User> findByStudentIdIgnoreCase(String studentId);

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);


    boolean existsByStudentId(String studentId);

    boolean existsByEmail(String email);
}
