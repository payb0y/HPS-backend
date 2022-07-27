package com.internship.internshipapp.service;

import com.internship.internshipapp.domain.Groupe;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.domain.User;
import com.internship.internshipapp.repo.GroupeRepo;
import com.internship.internshipapp.repo.RoleRepo;
import com.internship.internshipapp.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final GroupeRepo groupeRepo;
    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database",user.getUsername());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {} to the database",roleName,username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}",username);
        return userRepo.findByUsername(username);
    }
    public Role getRole(String name) {
        log.info("Fetching role {}",name);
        return roleRepo.findByName(name);
    }
    public Groupe getGroupe(String name) {
        log.info("Fetching group {}",name);
        return groupeRepo.findByName(name);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public Groupe saveGroupe(Groupe groupe) {
        log.info("Saving new groupe {} to the database",groupe.getName());
        return groupeRepo.save(groupe);
    }
    public void removeGroupe(int id) {
        log.info("removing groupe {} from the database", groupeRepo.findById(id));
        groupeRepo.deleteById(id);
    }

    @Override
    public void addUserToGroup(String username, String groupeName) {
        log.info("Adding user {} to groupe {}",username,groupeName);
        User user = userRepo.findByUsername(username);
        Groupe groupe = groupeRepo.findByName(groupeName);
        user.getGroupes().add(groupe);
    }
}
