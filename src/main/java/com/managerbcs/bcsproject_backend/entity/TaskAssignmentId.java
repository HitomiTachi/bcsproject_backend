package com.managerbcs.bcsproject_backend.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Data
// TaskAssignmentId.java
public class TaskAssignmentId implements Serializable {
    private Integer task;
    private Integer user;
// Equals And HashCode
}
