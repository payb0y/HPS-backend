package com.internship.internshipapp.repo;

import com.internship.internshipapp.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group, String> {
    Group findByName(String name);
}