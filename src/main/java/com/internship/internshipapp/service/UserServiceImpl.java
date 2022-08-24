package com.internship.internshipapp.service;

import com.internship.internshipapp.domain.Environment;
import com.internship.internshipapp.domain.Group;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.domain.User;
import com.internship.internshipapp.dto.EnvironmentDTO;
import com.internship.internshipapp.dto.GroupDTO;
import com.internship.internshipapp.dto.RoleDTO;
import com.internship.internshipapp.dto.UserDTO;
import com.internship.internshipapp.repo.EnvironmentRepo;
import com.internship.internshipapp.repo.GroupRepo;
import com.internship.internshipapp.repo.RoleRepo;
import com.internship.internshipapp.repo.UserRepo;
import com.internship.internshipapp.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    }
    @Override
    public List<GroupDTO> getGroups() {
        log.info("Fetching all groups");
        return groupRepo.findAll().stream().map(GroupDTO::new).collect(Collectors.toList());
    }
    public List<RoleDTO> getRole() {
        log.info("Fetching all roles");
        return roleRepo.findAll().stream().map(RoleDTO::new).collect(Collectors.toList());
    }
    public List<EnvironmentDTO> getEnvironments() {
        log.info("Fetching all environments");
        return environmentRepo.findAll().stream().map(EnvironmentDTO::new).collect(Collectors.toList());
    }
    public List<EnvironmentDTO> getUserEnvironments(String username){
        log.info("Fetching user {} environments",username);
        List<EnvironmentDTO> userEnvironments = new ArrayList<>();
        userRepo.findByUsername(username)
                .getGroups().forEach(group -> group.getEnvironments().forEach(environment -> userEnvironments.add(new EnvironmentDTO(environment))));
        return userEnvironments;
    }
    @Override
    public void addUser(String username) {
        log.info("Saving new user {} to the database",username);
        User user = new User();
        user.setUsername(username);
        userRepo.save(user);
    }
    @Override
    public void addGroup(GroupDTO groupDTO) {
        log.info("Saving new group {} to the database",groupDTO.getName());
        Group group = new Group();
        BeanUtils.copyProperties(groupDTO,group);
        groupRepo.save(group);
    }

   public void addEnvironment(EnvironmentDTO environmentDTO){
       log.info("Saving new environment {} to the database",environmentDTO.getName());
       Environment environment = new Environment();
       BeanUtils.copyProperties(environmentDTO,environment);
       environmentRepo.save(environment);
   }

    public UserDTO getUser(String username) {
        log.info("Fetching user {}",username);
        if(userRepo.findByUsername(username)==null)
            return null;
        UserDTO userDTO = new UserDTO(userRepo.findByUsername(username));
        return userDTO;
    }
    public EnvironmentDTO getEnvironment(EnvironmentDTO environment){
        log.info("Fetching environment {}",environment.getName());
        if(environmentRepo.findByName(environment.getName())==null)
            return null;
        EnvironmentDTO environmentDTO = new EnvironmentDTO(environmentRepo.findByName(environment.getName()));
        return environmentDTO;
    }
    public GroupDTO getGroup(GroupDTO group) {
        log.info("Fetching group {}",group.getName());
        if(groupRepo.findByName(group.getName())==null)
            return null;
        GroupDTO groupDTO = new GroupDTO(groupRepo.findByName(group.getName()));
        return groupDTO;
    }
    public void removeGroup(GroupDTO group) {
        log.info("removing group {} from the database", group.getName());
        groupRepo.delete(groupRepo.findByName(group.getName()));
    }
    public void removeEnvironment(EnvironmentDTO environment){
        log.info("removing environment {} from the database", environment.getName());
        environmentRepo.delete(environmentRepo.findByName(environment.getName()));
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



}
