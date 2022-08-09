package com.internship.internshipapp.service;

import com.internship.internshipapp.domain.Environment;
import com.internship.internshipapp.domain.Group;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.domain.User;
import com.internship.internshipapp.dto.GroupDTO;

import java.util.List;

public interface UserService {
    void addUser(String username);
    void addGroup(Group group);
    void addEnvironment(Environment environment);
    void addUserToGroups(String username,List<String> groupNames);
    void addEnvironmentsToGroup(String name,List<String> envNames);
    void addRoleToUser(String username,String name);
    void removeGroup(Group group);
    void removeEnvironment(Environment environment);
    User getUser(String username);
    Group getGroup(Group group);
    Environment getEnvironment(Environment environment);
    List<Object> getUsers();
    List<GroupDTO> getGroups();
    List<Role> getRoles();
    List<Environment> getEnvironments();
    List<Environment> getUserEnvironments(String username);


}
