package com.internship.internshipapp.repo;

import com.internship.internshipapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    User findByUsername(String username);
}
