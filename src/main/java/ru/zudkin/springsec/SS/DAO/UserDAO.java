package ru.zudkin.springsec.SS.DAO;

import ru.zudkin.springsec.SS.model.User;


import java.util.List;
import java.util.Optional;


public interface UserDAO {
    public void save(User user);

    public User find(int id);

    public List<User> findAll();

    public void update(User user);

    public void delete(int id);

    User findByEmail(String email);

}
