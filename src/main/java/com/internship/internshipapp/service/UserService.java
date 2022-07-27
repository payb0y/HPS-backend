package com.internship.internshipapp.service;

import com.internship.internshipapp.domain.Groupe;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.domain.User;

import java.util.List;

public interface UserService {
    void addUser(String username);
    void addRole(String roleName);
    void addGroup(String groupName);
    void removeGroup(String groupName);
    void addUserToGroup(String username,String groupName);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    Role getRole(String roleName);
    Groupe getGroup(String groupName);
    List<User> getUsers();


}
