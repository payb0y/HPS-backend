package com.internship.internshipapp.api;

import com.internship.internshipapp.domain.Groupe;
import com.internship.internshipapp.domain.Role;
import com.internship.internshipapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<List<Groupe>> getGroups(){
        return ResponseEntity.ok().body(userService.getGroups());
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok().body(userService.getRoles());
    }
    @PostMapping("/group/add")
    public ResponseEntity addGroup(HttpServletRequest request){
        userService.addGroup(request.getParameter("groupName"));
        return ResponseEntity.ok().build();
    }
    @PostMapping("/group/delete")
    public ResponseEntity deleteGroup(HttpServletRequest request){
        userService.removeGroup(request.getParameter("groupName"));
        return ResponseEntity.ok().build();
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
}
