package com.internship.internshipapp.service;

import com.internship.internshipapp.domain.Groupe;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    Groupe saveGroupe(Groupe groupe);
    void removeGroupe(int id);
    void addUserToGroup(String username,String groupeName);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    Role getRole(String name);
    Groupe getGroupe(String name);
    List<User> getUsers();


}
