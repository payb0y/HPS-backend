package com.internship.internshipapp.repo;

import com.internship.internshipapp.domain.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupeRepo extends JpaRepository<Groupe, Integer> {
    Groupe findByName(String name);
}