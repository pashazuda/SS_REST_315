package ru.zudkin.springsec.SS.DAO;

import ru.zudkin.springsec.SS.model.Role;
import ru.zudkin.springsec.SS.model.User;

import java.util.List;

public interface RoleDAO {
    List<Role> getRoles();
    public void save(Role role);
}
