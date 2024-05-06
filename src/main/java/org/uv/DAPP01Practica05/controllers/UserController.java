package org.uv.DAPP01Practica05.controllers;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.uv.DAPP01Practica05.user.User;

/**
 *
 * @author yodoeaoffi06
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "user")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(value = "user/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PutMapping(value = "user/{id}")
    public ResponseEntity<User> put(@PathVariable Long id, @RequestBody User user) {
        User actualUser = userService.put(id, user);
        if (actualUser != null) {
            return ResponseEntity.ok(actualUser);
        }

        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping(value = "user/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(userService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.notFound().build();
    }
}
