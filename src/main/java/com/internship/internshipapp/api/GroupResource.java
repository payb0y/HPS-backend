package com.internship.internshipapp.api;

import com.internship.internshipapp.domain.Group;
import com.internship.internshipapp.dto.GroupDTO;
import com.internship.internshipapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class GroupResource {
    private final UserService userService;

    @GetMapping("/groups")
    public ResponseEntity<List<GroupDTO>> getGroups() {
        return ResponseEntity.ok().body(userService.getGroups());
    }

    @PostMapping("/group/delete")
    public ResponseEntity deleteGroup(@RequestBody Group group) {
        if (userService.getGroup(group) != null) {
            userService.removeGroup(group);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("GROUP DOESN'T EXIST");
    }

    @PostMapping("/group/add")
    public ResponseEntity addGroup(@RequestBody Group group) {
        if (userService.getGroup(group) == null) {
            userService.addGroup(group);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("GROUP ALREADY EXISTS");
    }
    @PostMapping("/group/addEnvironmentsToGroup")
    public ResponseEntity addEnvironmentsToGroup(@RequestBody Map<String, Object> payload) {
        userService.addEnvironmentsToGroup((String) payload.get("name"), (List<String>) payload.get("names"));
        return ResponseEntity.ok().build();
    }

}
