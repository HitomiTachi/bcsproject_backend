package com.managerbcs.bcsproject_backend.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Data
// GroupMemberId.java
public class GroupMemberId implements Serializable {
    private Integer group;
    private Integer user;
// Equals And HashCode
}

