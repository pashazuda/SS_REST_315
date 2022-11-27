package ru.zudkin.springsec.SS.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.zudkin.springsec.SS.DAO.UserDAO;
import ru.zudkin.springsec.SS.DTO.RoleDTO;
import ru.zudkin.springsec.SS.DTO.UserDTO;
import ru.zudkin.springsec.SS.model.Role;
import ru.zudkin.springsec.SS.model.User;
import ru.zudkin.springsec.SS.service.RoleService;
import ru.zudkin.springsec.SS.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/admin")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    private final ModelMapper modelMapper;


    @Autowired
    public AdminRestController(UserService userService, RoleService roleService, ModelMapper modelMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/create-user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        userService.save(convertUserDtoToUser(userDTO));

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> usersList = userService.findAll().stream()
                .map(this::convertUserToUserDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping(value = "/get-user-by-id/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(convertUserToUserDto(userService.find(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/get-auth-user")
    public ResponseEntity<UserDTO> getAuthUser(Principal principal) {
        return new ResponseEntity<>(convertUserToUserDto(userService.findByEmail(principal.getName())), HttpStatus.OK);
    }

    @PutMapping (value = "/update-user")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserDTO userDTO) {
        userService.update(convertUserDtoToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value ="/get-roles")
    public ResponseEntity<List<RoleDTO>> getRoles() {
        List<RoleDTO> roles = roleService.getRoles().stream()
                .map(this::convertRoleToRoleDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    private User convertUserDtoToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO convertUserToUserDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private RoleDTO convertRoleToRoleDto(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }

    private Role convertRoleDtoToRole(RoleDTO roleDTO) {
        return modelMapper.map(roleDTO, Role.class);
    }

}
