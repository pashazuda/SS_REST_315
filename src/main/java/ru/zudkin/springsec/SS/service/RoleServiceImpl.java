package ru.zudkin.springsec.SS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zudkin.springsec.SS.DAO.RoleDAO;
import ru.zudkin.springsec.SS.model.Role;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> getRoles() {
        return roleDAO.getRoles();
    }

    @Transactional
    @Override
    public void save(Role role) {
        roleDAO.save(role);
    }

}
