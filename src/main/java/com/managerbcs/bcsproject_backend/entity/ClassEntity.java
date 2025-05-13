package com.managerbcs.bcsproject_backend.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Entity
@Table(name = "classes")
public class ClassEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classId;

    @Column(nullable = false)
    private String className;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;


    @OneToMany(mappedBy = "classEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ClassMember> members;

    @OneToMany(mappedBy = "classEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Assignment> assignments;

    @OneToMany(mappedBy = "classEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "classEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Group> groups;
    // Getters and Setters
}

