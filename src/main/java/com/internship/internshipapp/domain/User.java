package com.internship.internshipapp.domain;

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
    @ManyToMany(fetch= FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
    @ManyToMany(fetch= FetchType.LAZY)
    private Collection<Group> groups = new ArrayList<>();
}
