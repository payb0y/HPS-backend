package com.internship.internshipapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.internship.internshipapp.domain.Environment;
import com.internship.internshipapp.domain.Group;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Data @NoArgsConstructor @Slf4j
public class GroupDTO {
    private String name;
    @JsonIgnoreProperties("groups")
    private Collection<UserDTO> users = new ArrayList<>();
    @JsonIgnoreProperties("groups")
    private Collection<EnvironmentDTO> environments = new ArrayList<>();


    public GroupDTO(Group group) {
    log.info(group.getName());
        this.name = group.getName();
        this.users = group.getUsers().stream().map(user->{
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getUsername());
            userDTO.setGroups(null);
            userDTO.setRole(user.getRole());
            return userDTO;
        }).collect(Collectors.toList());

        this.environments = group.getEnvironments().stream().map(environment->{
            EnvironmentDTO environmentDTO = new EnvironmentDTO();
            environmentDTO.setName(environment.getName());
            environmentDTO.setGroups(null);
            return environmentDTO;
        }).collect(Collectors.toList());
    }
}
