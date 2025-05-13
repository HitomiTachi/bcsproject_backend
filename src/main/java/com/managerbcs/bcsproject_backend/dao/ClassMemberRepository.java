package com.managerbcs.bcsproject_backend.dao;

import com.managerbcs.bcsproject_backend.entity.ClassMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "thanh-vien-lop")
public interface ClassMemberRepository extends JpaRepository<ClassMember, Integer> {
}
