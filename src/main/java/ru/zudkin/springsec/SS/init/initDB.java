package ru.zudkin.springsec.SS.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.zudkin.springsec.SS.model.Role;
import ru.zudkin.springsec.SS.model.User;
import ru.zudkin.springsec.SS.repository.RoleRepository;
import ru.zudkin.springsec.SS.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class initDB implements ApplicationRunner {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public initDB(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role roleAdmin = new Role();
        Role roleUser = new Role();

//        roleUser.setId(2);
        roleUser.setName("ROLE_USER");
        roleRepository.save(roleUser);

//        roleAdmin.setId(1);
        roleAdmin.setName("ROLE_ADMIN");
        roleRepository.save(roleAdmin);


        User admin = new User();
        Set<Role> adminRoles = new HashSet<>();
        Collections.addAll(adminRoles, roleAdmin, roleUser);
//        admin.setId(1);
        admin.setFirstName("adminFirstName");
        admin.setLastName("adminLastName");
        admin.setAge(33);
        admin.setEmail("admin@mail");
        admin.setPassword("admin");
        admin.setRoles(adminRoles);
        userService.save(admin);

        User user = new User();
        Set<Role> userRoles = new HashSet<>();
        Collections.addAll(userRoles, roleUser);
//        user.setId(2);
        user.setFirstName("userFirstName");
        user.setLastName("userLastName");
        user.setAge(22);
        admin.setEmail("user@mail");
        user.setPassword("user");
        user.setRoles(userRoles);
        userService.save(user);
    }
}
