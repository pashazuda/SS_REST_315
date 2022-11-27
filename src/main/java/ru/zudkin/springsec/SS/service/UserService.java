package ru.zudkin.springsec.SS.service;

import ru.zudkin.springsec.SS.model.Role;
import ru.zudkin.springsec.SS.model.User;


import java.util.List;
import java.util.Set;


public interface UserService {

    public void save(User user);

    public User find(int id);

    public List<User> findAll();

    void update(User user);

    public void delete(int id);

    public User findByEmail(String email);

}
