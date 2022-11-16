package ru.zudkin.springsec.SS.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.zudkin.springsec.SS.service.UserService;


import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUserPage(Model model, Principal principal) {
        Integer userId = userService.findByEmail(principal.getName()).getId();
        model.addAttribute("user", userService.find(userId));
        return "user/user-page";
    }

}
