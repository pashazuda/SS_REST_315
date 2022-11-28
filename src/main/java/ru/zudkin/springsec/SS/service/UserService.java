package ru.zudkin.springsec.SS.service;

import ru.zudkin.springsec.SS.dto.UserDTO;


import java.util.List;


public interface UserService {

    public void save(UserDTO userDTO);

    public UserDTO find(int id);

    public List<UserDTO> findAll();

    void update(UserDTO userDTO);

    public void delete(int id);

    public UserDTO findByEmail(String email);

}
