package com.bootcamp.library.controller;

import com.bootcamp.library.model.domain.User;
import com.bootcamp.library.model.dto.UserDTO;
import com.bootcamp.library.service.InitService;
import com.bootcamp.library.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private InitService initService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UserDTO> add(@RequestBody UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);

        User savedUser = userService.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedUser, UserDTO.class));
    }

    @PutMapping("/initialize")
    public ResponseEntity initialize() {
        initService.init();
        return ResponseEntity.noContent().build();
    }

}
