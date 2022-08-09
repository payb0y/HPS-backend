package com.internship.internshipapp.api;

import com.internship.internshipapp.domain.Environment;
import com.internship.internshipapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class EnvironmentResource {
    private final UserService userService;

    @GetMapping("/environments")
    public ResponseEntity<List<Environment>> getEnvironments() {
        return ResponseEntity.ok().body(userService.getEnvironments());
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
        if (userService.getEnvironment(environment) != null) {
            userService.removeEnvironment(environment);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("ENVIRONMENT DOESN'T EXIST");
    }
}
