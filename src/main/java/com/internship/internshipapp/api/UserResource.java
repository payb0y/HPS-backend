package com.internship.internshipapp.api;

import com.internship.internshipapp.domain.Group;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.domain.User;
import com.internship.internshipapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserResource {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<Object>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getGroups(){
        return ResponseEntity.ok().body(userService.getGroups());
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok().body(userService.getRoles());
    }

    @PostMapping("/user/addUserToGroups")
    public ResponseEntity addUserToGroups(@RequestBody Map<String, Object> payload){
        userService.addUserToGroups((String) payload.get("username"), (List<String>) payload.get("names"));
        return ResponseEntity.ok().build();
    }
    @PostMapping("/user/addRolesToUser")
    public ResponseEntity addRolesToUser(@RequestBody Map<String, Object> payload){
        userService.addRolesToUser((String) payload.get("username"), (List<String>) payload.get("names"));
        return ResponseEntity.ok().build();
    }
    @PostMapping("/user/getUserGroups")
    public ResponseEntity<Collection<Group>> getUserGroups(@RequestBody User user){
        return ResponseEntity.ok().body(userService.getUser(user.getUsername()).getGroups());
    }
    @PostMapping("/group/delete")
    public ResponseEntity deleteGroup(@RequestBody Group group){
        userService.removeGroup(group.getName());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/group/add")
    public ResponseEntity addGroup(@RequestBody Group group){
        userService.addGroup(group);
        return ResponseEntity.ok().build();
    }
}
