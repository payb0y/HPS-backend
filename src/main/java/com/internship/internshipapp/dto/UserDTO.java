package com.internship.internshipapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.internship.internshipapp.domain.Group;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Data @NoArgsConstructor @Slf4j
public class UserDTO {
    private String username;
    private Role role;
    @JsonIgnoreProperties("users")
    private Collection<GroupDTO> groups = new ArrayList<>();
    public UserDTO(User user) {
        this.username = user.getUsername();
        this.role = user.getRole();
        this.groups = user.getGroups().stream().map(group->{
            GroupDTO groupDTO = new GroupDTO();
            groupDTO.setUsers(null);
            groupDTO.setName(group.getName());
            groupDTO.setEnvironments(group.getEnvironments().stream().map(EnvironmentDTO::new).collect(Collectors.toList()));
            return groupDTO;
        }).collect(Collectors.toList());
    }
}
