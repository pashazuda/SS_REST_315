package ru.zudkin.springsec.SS.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zudkin.springsec.SS.DTO.UserDTO;
import ru.zudkin.springsec.SS.model.User;
import ru.zudkin.springsec.SS.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("api/user")
public class UserRestController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRestController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<UserDTO> getAuthUser(Principal principal) {
        System.out.println(principal.getName());
        return new ResponseEntity<>(convertUserToUserDto(userService.findByEmail(principal.getName())), HttpStatus.OK);
    }

    private UserDTO convertUserToUserDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
