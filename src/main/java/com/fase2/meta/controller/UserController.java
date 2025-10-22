package com.fase2.meta.controller;

import com.fase2.meta.dto.LoginDTO;
import com.fase2.meta.dto.UserDTO;
import com.fase2.meta.model.User;
import com.fase2.meta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if(userService.save(user)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) {
        UserDTO user = userService.findByLogin(loginDTO);
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
