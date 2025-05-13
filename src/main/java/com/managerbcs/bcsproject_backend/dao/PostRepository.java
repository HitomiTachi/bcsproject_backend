package com.managerbcs.bcsproject_backend.dao;

import com.managerbcs.bcsproject_backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "thong-bao")
public interface PostRepository extends JpaRepository<Post, Integer> {
}
