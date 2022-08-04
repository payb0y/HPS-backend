package com.internship.internshipapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`group`")
public class Group {
    @Id
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("groups")
    @JoinTable(name="environment_groups",
    joinColumns = @JoinColumn(name="groups_name"),
    inverseJoinColumns = @JoinColumn(name="environment_name"))
    private Collection<Environment> environments = new ArrayList<>();

    @ManyToMany(mappedBy = "groups",fetch = FetchType.LAZY)
    @JsonIgnoreProperties("groups")
    private Collection<User> users = new ArrayList<>();

}
