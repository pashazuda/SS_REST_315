package ru.zudkin.springsec.SS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zudkin.springsec.SS.DAO.RoleDAO;
import ru.zudkin.springsec.SS.DAO.UserDAO;
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

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public void save(User user) {
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
    public User find(int id) {
        return userDAO.find(id);
    }


    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Transactional
    @Override
    public void update(User user) {
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
    public User findByEmail(String email) {
        System.out.println(email);
        return userDAO.findByEmail(email);
    }


}
