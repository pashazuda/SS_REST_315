package ru.zudkin.springsec.SS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zudkin.springsec.SS.model.User;
import ru.zudkin.springsec.SS.service.RoleService;
import ru.zudkin.springsec.SS.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/admin-page";
    }

    @GetMapping("/user-page")
    public String showUserPage(Model model, Principal principal) {
        Integer userId = userService.findByEmail(principal.getName()).getId();
        model.addAttribute("user", userService.find(userId));
        return "admin/user-page";
    }


    @GetMapping("/create-user")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getRoles());
        return "admin/create-user-page";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/create-user-page";
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/update-user")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.find(id));
        model.addAttribute("roles", roleService.getRoles());
        return "admin/update-user-page";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "admin/update-user-page";
        }
        userService.update(id, user);
        return "redirect:/admin";
    }

    @GetMapping("/delete-user/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}