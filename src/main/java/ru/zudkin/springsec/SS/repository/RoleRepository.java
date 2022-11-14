package ru.zudkin.springsec.SS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zudkin.springsec.SS.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
