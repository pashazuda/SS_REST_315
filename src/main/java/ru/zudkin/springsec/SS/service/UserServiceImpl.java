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
    @Override
    public void save(User user, String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(roleDAO.getRoleByName(role));
        }
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Transactional
    public void save(User user) {
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
    public void update(int id, User user, String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(roleDAO.getRoleByName(role));
        }
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.update(id, user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }


}
