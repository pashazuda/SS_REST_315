package ru.zudkin.springsec.SS.dao;

import ru.zudkin.springsec.SS.model.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getRoles();
    public void save(Role role);

    public Role getRoleByName(String name);
}
