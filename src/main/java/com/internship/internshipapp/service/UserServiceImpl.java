package com.internship.internshipapp.service;

import com.internship.internshipapp.domain.Group;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.domain.User;
import com.internship.internshipapp.repo.GroupRepo;
import com.internship.internshipapp.repo.RoleRepo;
import com.internship.internshipapp.repo.UserRepo;
import com.internship.internshipapp.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final GroupRepo groupRepo;
    @Override
    public void addUser(String username) {
        log.info("Saving new user {} to the database",username);
        User user = new User();
        user.setUsername(username);
        userRepo.save(user);
    }
    @Override
    public void addRole(String roleName) {
        log.info("Saving new role {} to the database",roleName);
        Role role = new Role();
        role.setName(roleName);
        roleRepo.save(role);
    }
    @Override
    public void addGroup(Group group) {
        log.info("Saving new group {} to the database",group.getName());
        groupRepo.save(group);
    }
    @Override
    public List<Object> getUsers(){
        log.info("Fetching all users");
        return Utility.appendLdapGroupsToUsers(userRepo.findAll());
    }
    @Override
    public List<Group> getGroups() {
        log.info("Fetching all groups");
        return groupRepo.findAll();
    }
    public List<Role> getRoles() {
        log.info("Fetching all roles");
        return roleRepo.findAll();
    }
    @Override
    public User getUser(String username) {
        log.info("Fetching user {}",username);
        return userRepo.findByUsername(username);
    }
    public Role getRole(String roleName) {
        log.info("Fetching role {}",roleName);
        return roleRepo.findByName(roleName);
    }
    public Group getGroup(String groupName) {
        log.info("Fetching group {}",groupName);
        return groupRepo.findByName(groupName);
    }
    public void addUserToGroups(String username, List<String> groupNames) {
        log.info("Adding user {} to groups {}",username,groupNames);
        User user = userRepo.findByUsername(username);
        user.getGroups().clear();
        groupNames.forEach(group->user.getGroups().add(groupRepo.findByName(group)));

    }
    public void addRolesToUser(String username, List<String> roleNames) {
        log.info("Adding roles {} to user {}",roleNames,username);
        User user = userRepo.findByUsername(username);
        user.getRoles().clear();
        roleNames.forEach(role->user.getRoles().add(roleRepo.findByName(role)));

    }
    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}",roleName,username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }
    public void removeGroup(String groupName) {
        log.info("removing group {} from the database", groupName);
        groupRepo.delete(groupRepo.findByName(groupName));
    }


}
