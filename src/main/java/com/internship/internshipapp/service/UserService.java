package com.internship.internshipapp.service;

import com.internship.internshipapp.domain.Environment;
import com.internship.internshipapp.domain.Group;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.domain.User;
import com.internship.internshipapp.dto.EnvironmentDTO;
import com.internship.internshipapp.dto.GroupDTO;
import com.internship.internshipapp.dto.RoleDTO;
import com.internship.internshipapp.dto.UserDTO;

import java.util.List;

public interface UserService {
    void addUser(String username);
    void addGroup(GroupDTO group);
    void addEnvironment(EnvironmentDTO environment);
    void addUserToGroups(String username,List<String> groupNames);
    void addEnvironmentsToGroup(String name,List<String> envNames);
    void addRoleToUser(String username,String name);
    void removeGroup(GroupDTO group);
    void removeEnvironment(EnvironmentDTO environment);
    UserDTO getUser(String username);
    GroupDTO getGroup(GroupDTO group);
    EnvironmentDTO getEnvironment(EnvironmentDTO environment);
    List<Object> getUsers();
    List<GroupDTO> getGroups();
    List<RoleDTO> getRole();
    List<EnvironmentDTO> getEnvironments();
    List<EnvironmentDTO> getUserEnvironments(String username);


}
