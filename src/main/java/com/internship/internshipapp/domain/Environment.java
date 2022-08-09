package com.internship.internshipapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Environment {
    @Id
    private String name;

    @ManyToMany(mappedBy = "environments", fetch = FetchType.EAGER)
    private Collection<Group> groups = new ArrayList<>();
}
