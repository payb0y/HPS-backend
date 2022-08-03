package com.internship.internshipapp.service;

import com.internship.internshipapp.domain.Group;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.domain.User;

import java.util.List;

public interface UserService {
    void addUser(String username);
    void addRole(String roleName);
    void addGroup(Group group);
    void addUserToGroups(String username,List<String> groupNames);
    void addRolesToUser(String username,List<String> roleNames);
    void addRoleToUser(String username,String name);
    void removeGroup(String groupName);
    User getUser(String username);
    Role getRole(String roleName);
    Group getGroup(String groupName);
    List<Object> getUsers();
    List<Group> getGroups();
    List<Role> getRoles();


}
