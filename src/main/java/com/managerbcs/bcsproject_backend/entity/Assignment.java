package com.managerbcs.bcsproject_backend.entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Entity
@Table(name = "assignments")
public class Assignment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assignmentId;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;


    private String title;
    private String description;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "assignment")
    private List<AssignmentSubmission> submissions;
    // Getters and Setters

}

