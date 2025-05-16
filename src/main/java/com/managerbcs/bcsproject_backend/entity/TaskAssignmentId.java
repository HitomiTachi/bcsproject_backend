package com.managerbcs.bcsproject_backend.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class TaskAssignmentId implements Serializable {
    private Integer taskId; // Tương ứng với task_id
    private Integer userId; // Tương ứng với user_id
}
