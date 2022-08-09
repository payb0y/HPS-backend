package com.internship.internshipapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    private String username;
    @ManyToOne
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_groups",
            joinColumns = @JoinColumn(name="user_username"),
            inverseJoinColumns = @JoinColumn(name="groups_name"))
    private Collection<Group> groups = new ArrayList<>();
}
