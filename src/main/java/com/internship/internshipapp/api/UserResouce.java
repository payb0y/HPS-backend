package com.internship.internshipapp.api;

import com.internship.internshipapp.domain.Groupe;
import com.internship.internshipapp.domain.Role;
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
public class UserResouce {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/group/save")
    public ResponseEntity<Groupe> saveGroupe(HttpServletRequest request){
        Groupe groupe = new Groupe();
        groupe.setName(request.getParameter("groupName"));
        userService.saveGroupe(groupe);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/group/delete")
    public ResponseEntity<Groupe> deleteGroupe(HttpServletRequest request){

        userService.removeGroupe(Integer.parseInt(request.getParameter("groupId")));
        return ResponseEntity.ok().build();
    }
    @PostMapping("/user/addUserToGroup")
    public ResponseEntity<Groupe> addUserToGroup(HttpServletRequest request){
        if (userService.getUser(request.getParameter("username"))
                .getGroupes()
                .contains(userService.getGroupe(request.getParameter("groupName")))){
            return ResponseEntity.badRequest().build();
        }
        userService.addUserToGroup(request.getParameter("username"),request.getParameter("groupName"));
        return ResponseEntity.ok().build();
    }
}
