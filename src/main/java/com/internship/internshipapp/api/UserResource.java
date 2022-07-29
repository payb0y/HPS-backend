package com.internship.internshipapp.api;

import com.internship.internshipapp.domain.Groupe;
import com.internship.internshipapp.domain.User;
import com.internship.internshipapp.service.LdapService;
import com.internship.internshipapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserResource {
    private final UserService userService;
    private LdapService ldapService = new LdapService();

    @GetMapping("/users")
    public ResponseEntity<List<Object>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    @GetMapping("/groups")
    public ResponseEntity<List<Groupe>> getGroup(){
        return ResponseEntity.ok().body(userService.getGroups());
    }
    @PostMapping("/group/getLdapGroups")
    public ResponseEntity getLdapGroups(HttpServletRequest request) throws NamingException {
        ;
        return ResponseEntity.ok().body(ldapService.getAllGroups(request.getParameter("username")));
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

    @PostMapping("/user/addUserToGroup")
    public ResponseEntity addUserToGroup(HttpServletRequest request){
        userService.addUserToGroup(request.getParameter("username"),request.getParameter("groupName"));
        return ResponseEntity.ok().build();
    }
    @PostMapping("/user/addUserToGroups")
    public ResponseEntity addUserToGroups(@RequestBody Map<String, Object> payload){
        userService.addUserToGroups((String) payload.get("username"), (List<String>) payload.get("groupNames"));
        return ResponseEntity.ok().build();
    }
}
