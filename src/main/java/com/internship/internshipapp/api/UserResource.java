package com.internship.internshipapp.api;

import com.internship.internshipapp.domain.Environment;
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
    public ResponseEntity<List<Object>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getGroups() {
        return ResponseEntity.ok().body(userService.getGroups());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok().body(userService.getRoles());
    }

    @GetMapping("/environments")
    public ResponseEntity<List<Environment>> getEnvironments() {
        return ResponseEntity.ok().body(userService.getEnvironments());
    }

    @PostMapping("/user/addUserToGroups")
    public ResponseEntity addUserToGroups(@RequestBody Map<String, Object> payload) {
        userService.addUserToGroups((String) payload.get("username"), (List<String>) payload.get("names"));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/getUserGroups")
    public ResponseEntity<Collection<Group>> getUserGroups(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.getUser(user.getUsername()).getGroups());
    }

    @PostMapping("/user/getUserEnvironments")
    public ResponseEntity<Collection<Environment>> getUserEnvironments(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.getUserEnvironments(user.getUsername()));
    }

    @PostMapping("/group/addEnvironmentsToGroup")
    public ResponseEntity addEnvironmentsToGroup(@RequestBody Map<String, Object> payload) {
        userService.addEnvironmentsToGroup((String) payload.get("name"), (List<String>) payload.get("names"));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/group/delete")
    public ResponseEntity deleteGroup(@RequestBody Group group) {
        userService.removeGroup(group.getName());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/user/addRoleToUser")
    public ResponseEntity addRoleToUser(@RequestBody Map<String, Object> payload) {
        userService.addRoleToUser((String) payload.get("username"), (String) payload.get("name"));
        return ResponseEntity.ok().build();
    }
    @PostMapping("/group/add")
    public ResponseEntity addGroup(@RequestBody Group group) {
        if (userService.getGroup(group) == null) {
            userService.addGroup(group);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("GROUP ALREADY EXISTS");
    }
    @PostMapping("/environment/add")
    public ResponseEntity addEnvironment(@RequestBody Environment environment) {
        if (userService.getEnvironment(environment) == null) {
            userService.addEnvironment(environment);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("ENVIRONMENT ALREADY EXISTS");
    }
    @PostMapping("/environment/delete")
    public ResponseEntity deleteEnvironment(@RequestBody Environment environment) {
        log.info(userService.getEnvironment(environment).getName());
        if (userService.getEnvironment(environment) != null) {
            userService.removeEnvironment(environment);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("ENVIRONMENT DOESN'T EXIST");
    }
}
