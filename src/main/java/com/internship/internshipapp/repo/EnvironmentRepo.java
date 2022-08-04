package com.internship.internshipapp.repo;

import com.internship.internshipapp.domain.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvironmentRepo extends JpaRepository<Environment, String> {
    Environment findByName(String name);
}