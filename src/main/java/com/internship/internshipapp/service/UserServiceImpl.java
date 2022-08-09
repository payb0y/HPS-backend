package com.internship.internshipapp.service;

import com.internship.internshipapp.domain.Environment;
import com.internship.internshipapp.domain.Group;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.domain.User;
import com.internship.internshipapp.dto.GroupDTO;
import com.internship.internshipapp.dto.UserDTO;
import com.internship.internshipapp.repo.EnvironmentRepo;
import com.internship.internshipapp.repo.GroupRepo;
import com.internship.internshipapp.repo.RoleRepo;
import com.internship.internshipapp.repo.UserRepo;
import com.internship.internshipapp.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final GroupRepo groupRepo;
    private final EnvironmentRepo environmentRepo;
    @Override
    public List<Object> getUsers(){
        log.info("Fetching all users");
        return Utility.appendLdapGroupsToUsers(userRepo.findAll().stream().map(UserDTO::new).collect(Collectors.toList()));
        //return Utility.appendLdapGroupsToUsers(userRepo.findAll());
    }
    @Override
    public List<GroupDTO> getGroups() {
        log.info("Fetching all groups");
        return groupRepo.findAll().stream().map(GroupDTO::new).collect(Collectors.toList());
    }
    @Override
    public void addUser(String username) {
        log.info("Saving new user {} to the database",username);
        User user = new User();
        user.setUsername(username);
        userRepo.save(user);
    }
    @Override
    public void addGroup(Group group) {
        log.info("Saving new group {} to the database",group.getName());
        groupRepo.save(group);
    }

   public void addEnvironment(Environment environment){
       log.info("Saving new environment {} to the database",environment.getName());
       environmentRepo.save(environment);
   }

    public List<Role> getRoles() {
        log.info("Fetching all roles");
        return roleRepo.findAll();
    }
    public List<Environment> getEnvironments() {
        log.info("Fetching all environments");
        return environmentRepo.findAll();
    }
    public List<Environment> getUserEnvironments(String username){
        log.info("Fetching user {} environments",username);
        List<Environment> userEnvironments = new ArrayList<>();
        userRepo.findByUsername(username)
                .getGroups().forEach(group -> group.getEnvironments().forEach(environment -> userEnvironments.add(environment)));
         return userEnvironments;
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}",username);
        return userRepo.findByUsername(username);
    }
    public Group getGroup(Group group) {
        log.info("Fetching group {}",group.getName());
        return groupRepo.findByName(group.getName());
    }
    public Environment getEnvironment(Environment environment){
        log.info("Fetching environment {}",environment.getName());
        return environmentRepo.findByName(environment.getName());
    }

    public void addUserToGroups(String username, List<String> groupNames) {
        log.info("Adding user {} to groups {}",username,groupNames);
        User user = userRepo.findByUsername(username);
        user.getGroups().clear();
        user.getGroups().forEach(group -> log.info(group.getName()));
        groupNames.forEach(group->user.getGroups().add(groupRepo.findByName(group)));

    }
    public void addEnvironmentsToGroup(String name, List<String> envNames) {
        log.info("Adding environments {} to group {}",envNames,name);
        Group group = groupRepo.findByName(name);
        group.getEnvironments().clear();
        envNames.forEach(env->group.getEnvironments().add(environmentRepo.findByName(env)));

    }
    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}",roleName,username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.setRole(role);
    }
    public void removeGroup(Group group) {
        log.info("removing group {} from the database", group.getName());
        groupRepo.delete(groupRepo.findByName(group.getName()));
    }
    public void removeEnvironment(Environment environment){
        log.info("removing environment {} from the database", environment.getName());
        environmentRepo.delete(environment);
    }

}
