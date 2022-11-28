package ru.zudkin.springsec.SS.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zudkin.springsec.SS.dao.RoleDAO;
import ru.zudkin.springsec.SS.dao.UserDAO;
import ru.zudkin.springsec.SS.dto.UserDTO;
import ru.zudkin.springsec.SS.model.Role;
import ru.zudkin.springsec.SS.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public void save(UserDTO userDTO) {
        User user = convertUserDtoToUser(userDTO);
        Set<Role> roleSet = new HashSet<>();
        user.getRoles().forEach(role -> {
            Role roleByName = roleDAO.getRoleByName(role.getName());
            roleByName.getUsers().add(user);
            roleDAO.save(roleByName);
            roleSet.add(roleByName);
        });
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }


    @Override
    public UserDTO find(int id) {
        return convertUserToUserDto(userDAO.find(id));
    }


    @Override
    public List<UserDTO> findAll() {
        return userDAO.findAll().stream()
                .map(this::convertUserToUserDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void update(UserDTO userDTO) {
        User user = convertUserDtoToUser(userDTO);
        Set<Role> roleSet = new HashSet<>();
        user.getRoles().forEach(role -> {
            Role roleByName = roleDAO.getRoleByName(role.getName());
            roleByName.getUsers().add(user);
            roleDAO.save(roleByName);
            roleSet.add(roleByName);
        });
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.update(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    public UserDTO findByEmail(String email) {
        return convertUserToUserDto(userDAO.findByEmail(email));
    }

    private User convertUserDtoToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO convertUserToUserDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }



}
