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
    public void addUser(String username) {
        log.info("Saving new user {} to the database",username);
        User user = new User();
        user.setUsername(username);
        userRepo.save(user);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}",username);
        return userRepo.findByUsername(username);
    }

    @Override
    public void addUserToGroup(String username, String groupeName) {
        log.info("Adding user {} to group {}",username,groupeName);
        User user = userRepo.findByUsername(username);
        Groupe groupe = groupeRepo.findByName(groupeName);
        user.getGroupes().add(groupe);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}",roleName,username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public void addRole(String roleName) {
        log.info("Saving new role {} to the database",roleName);
        Role role = new Role();
        role.setName(roleName);
        roleRepo.save(role);
    }

    public Role getRole(String roleName) {
        log.info("Fetching role {}",roleName);
        return roleRepo.findByName(roleName);
    }

    public Groupe getGroup(String groupName) {
        log.info("Fetching group {}",groupName);
        return groupeRepo.findByName(groupName);
    }


    @Override
    public void addGroup(String groupName) {
        log.info("Saving new groupe {} to the database",groupName);
        Groupe group = new Groupe();
        group.setName(groupName);
        groupeRepo.save(group);
    }

    public void removeGroup(String groupName) {
        log.info("removing groupe {} from the database", groupName);
        groupeRepo.delete(groupeRepo.findByName(groupName));
    }


}
