package com.cineflix.vn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "RoleUS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @Column
    private String idRole;
    @Column
    private String roleName;
    @Column
    private String description;
    // foregion key
    @OneToMany(mappedBy = "role")
    @Column
    List<RoleMapping> roleMappings;

    public Role(String idRole, String roleName, String description) {
        this.idRole = idRole;
        this.roleName = roleName;
        this.description = description;
    }
}
