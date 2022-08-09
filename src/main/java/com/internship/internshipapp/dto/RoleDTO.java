package com.internship.internshipapp.dto;

import com.internship.internshipapp.domain.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class RoleDTO {
    private String name;
    public RoleDTO(Role role){
        this.name = role.getName();
    }
}
