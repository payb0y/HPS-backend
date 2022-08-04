package com.internship.internshipapp.service;

import com.internship.internshipapp.domain.Environment;
import com.internship.internshipapp.domain.Group;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.domain.User;

import java.util.List;

public interface UserService {
    void addUser(String username);
    void addRole(String roleName);
    void addGroup(Group group);
    void addEnvironment(Environment environment);
    void addUserToGroups(String username,List<String> groupNames);
    void addEnvironmentsToGroup(String name,List<String> envNames);
    void addRoleToUser(String username,String name);
    void removeGroup(String groupName);
    void removeEnvironment(Environment environment);
    User getUser(String username);
    Role getRole(String roleName);
    Group getGroup(Group group);
    Group getGroup(String name);
    Environment getEnvironment(Environment environment);
    Environment getEnvironment(String environment);
    List<Object> getUsers();
    List<Group> getGroups();
    List<Role> getRoles();
    List<Environment> getEnvironments();
    List<Environment> getUserEnvironments(String username);


}
