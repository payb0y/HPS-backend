package com.internship.internshipapp.service;

import com.internship.internshipapp.domain.Groupe;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.domain.User;

import java.util.List;

public interface UserService {
    void addUser(String username);
    void addRole(String roleName);
    void addGroup(String groupName);
    void addUserToGroups(String username,List<String> groupNames);
    void addRolesToUser(String username,List<String> roleNames);
    void addRoleToUser(String username,String name);
    void removeGroup(String groupName);
    User getUser(String username);
    Role getRole(String roleName);
    Groupe getGroup(String groupName);
    List<Object> getUsers();
    List<Groupe> getGroups();
    List<Role> getRoles();


}
