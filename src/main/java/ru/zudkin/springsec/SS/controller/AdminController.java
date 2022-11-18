package ru.zudkin.springsec.SS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zudkin.springsec.SS.model.Role;
import ru.zudkin.springsec.SS.model.User;
import ru.zudkin.springsec.SS.service.RoleService;
import ru.zudkin.springsec.SS.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String index(Model model, Principal principal, @ModelAttribute ("user") User user) {
        Integer id = userService.findByEmail(principal.getName()).getId();
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", userService.find(id));
        model.addAttribute("rolesList", roleService.getRoles());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping("/create-new-user")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam("roles") String[] roles) {
        userService.save(user, roles);
        return "redirect:/admin";
    }


    @PatchMapping("/user-update/{id}")
    public String update(User user, @RequestParam("roles") String[] roles,
                         @PathVariable("id") int id) {
        userService.update(id, user, roles);
        return "redirect:/admin";
    }

    @DeleteMapping("/user-delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}