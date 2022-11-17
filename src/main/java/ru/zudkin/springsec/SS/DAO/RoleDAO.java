package ru.zudkin.springsec.SS.DAO;

import ru.zudkin.springsec.SS.model.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getRoles();
    public void save(Role role);

    public Role getRoleByName(String role);
}
