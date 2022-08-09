package com.internship.internshipapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

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
    @JsonIgnoreProperties("groups")
    @ManyToMany(mappedBy = "groups",fetch = FetchType.LAZY)
    private Collection<User> users = new ArrayList<>();

}
