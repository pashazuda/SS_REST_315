package ru.zudkin.springsec.SS.service;

import ru.zudkin.springsec.SS.model.User;


import java.util.List;


public interface UserService {
    public void save(User user, String[] roles);

    public void save(User user);

    public User find(int id);

    public List<User> findAll();

    public void update(int id, User user, String[] roles);

    public void delete(int id);

    public User findByEmail(String email);

}
