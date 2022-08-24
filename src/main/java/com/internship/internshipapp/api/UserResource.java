package com.internship.internshipapp.api;

import com.internship.internshipapp.domain.Environment;
import com.internship.internshipapp.domain.Group;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.domain.User;
import com.internship.internshipapp.dto.EnvironmentDTO;
import com.internship.internshipapp.dto.GroupDTO;
import com.internship.internshipapp.dto.RoleDTO;
import com.internship.internshipapp.dto.UserDTO;
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
    @GetMapping("/user/getUser")
    public ResponseEntity<UserDTO> getUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.getUser(user.getUsername()));
    }
    @GetMapping("/roles")
    public ResponseEntity<List<RoleDTO>> getRoles() {
        return ResponseEntity.ok().body(userService.getRole());
    }

    @PostMapping("/user/addUserToGroups")
    public ResponseEntity addUserToGroups(@RequestBody Map<String, Object> payload) {
        if (userService.getUser((String) payload.get("username")) != null) {
            userService.addUserToGroups((String) payload.get("username"), (List<String>) payload.get("names"));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("USER DOESN'T EXIST");
    }

    @PostMapping("/user/getUserGroups")
    public ResponseEntity<Collection<GroupDTO>> getUserGroups(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.getUser(user.getUsername()).getGroups());
    }

    @PostMapping("/user/getUserEnvironments")
    public ResponseEntity<Collection<EnvironmentDTO>> getUserEnvironments(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.getUserEnvironments(user.getUsername()));
    }

    @PostMapping("/user/addRoleToUser")
    public ResponseEntity addRoleToUser(@RequestBody Map<String, Object> payload) {
        if (userService.getUser((String) payload.get("username")) != null) {
            userService.addRoleToUser((String) payload.get("username"), (String) payload.get("name"));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("USER DOESN'T EXIST");
    }


}
