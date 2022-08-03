package com.internship.internshipapp.repo;

import com.internship.internshipapp.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, String> {
    Role findByName(String name);
}
