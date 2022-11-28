package ru.zudkin.springsec.SS.dao;

import ru.zudkin.springsec.SS.model.User;


import java.util.List;


public interface UserDAO {
    public void save(User user);

    public User find(int id);

    public List<User> findAll();

    public void update(User user);

    public void delete(int id);

    User findByEmail(String email);

}
