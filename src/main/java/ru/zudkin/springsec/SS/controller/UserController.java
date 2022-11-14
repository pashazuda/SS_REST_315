package ru.zudkin.springsec.SS.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.zudkin.springsec.SS.repository.UserRepository;
import ru.zudkin.springsec.SS.service.UserDetailsServiceImpl;
import ru.zudkin.springsec.SS.service.UserService;


import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserDetailsServiceImpl userDetailsServiceImpl, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showUserPage(Model model, Principal principal) {
        Integer userId = userRepository.findByEmail(principal.getName()).get().getId();
        model.addAttribute("user", userService.find(userId));
        return "user/user-page";
    }

}
