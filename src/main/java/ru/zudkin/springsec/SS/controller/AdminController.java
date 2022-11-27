package ru.zudkin.springsec.SS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zudkin.springsec.SS.model.User;
import ru.zudkin.springsec.SS.service.RoleService;
import ru.zudkin.springsec.SS.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping
public class AdminController {

    @GetMapping("/admin")
    public String index() {
        return "admin";
    }


}