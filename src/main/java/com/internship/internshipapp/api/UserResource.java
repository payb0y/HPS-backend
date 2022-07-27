package com.internship.internshipapp.api;

import com.internship.internshipapp.domain.User;
import com.internship.internshipapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
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
}
