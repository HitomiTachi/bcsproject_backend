package com.managerbcs.bcsproject_backend.dao;

import com.managerbcs.bcsproject_backend.entity.Comment;
import com.managerbcs.bcsproject_backend.entity.GroupMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "binh-luan")
public interface CommentRepository extends JpaRepository<Comment, GroupMemberId> {
}
