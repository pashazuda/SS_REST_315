package ru.zudkin.springsec.SS.service;

import ru.zudkin.springsec.SS.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();
    void save(Role role);

}
