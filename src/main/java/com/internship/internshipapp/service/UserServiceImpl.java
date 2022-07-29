package com.internship.internshipapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.internshipapp.domain.Groupe;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.domain.User;
import com.internship.internshipapp.repo.GroupeRepo;
import com.internship.internshipapp.repo.RoleRepo;
import com.internship.internshipapp.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final GroupeRepo groupeRepo;
    private LdapService ldapService = new LdapService();
    @Override
    public void addUser(String username) {
        log.info("Saving new user {} to the database",username);
        User user = new User();
        user.setUsername(username);
        userRepo.save(user);
    }
    @Override
    public List<Object> getUsers() {
        log.info("Fetching all users");
        List<Object> users = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        userRepo.findAll().forEach(user-> {
            try {
                Map<String, Object> map = objectMapper.convertValue(user, Map.class);
                map.put("ldapGroups",ldapService.getAllGroups(user.getUsername()));
                users.add(map);
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }});
        return users;
    }
    @Override
    public List<Groupe> getGroups() {
        log.info("Fetching all groups");
        return groupeRepo.findAll();
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
    public void addUserToGroups(String username, List<String> groupeName) {
        log.info("Adding user {} to group {}",username,groupeName);
        User user = userRepo.findByUsername(username);
        user.getGroupes().clear();
        groupeName.forEach(group->{
            Groupe groupe = groupeRepo.findByName(group);
            user.getGroupes().add(groupe);
        });

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
