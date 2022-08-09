package com.internship.internshipapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.internship.internshipapp.domain.Environment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Data @NoArgsConstructor
public class EnvironmentDTO {
    private String name;
    @JsonIgnoreProperties("environments")
    private Collection<GroupDTO> groups = new ArrayList<>();

    public EnvironmentDTO(Environment environment){
        this.name = environment.getName();
        groups = environment.getGroups().stream().map(group->{
            GroupDTO groupDTO = new GroupDTO(group);
            groupDTO.setEnvironments(null);
            return groupDTO;
        }).collect(Collectors.toList());
    }
}
