package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserService;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("getAllUsers", users);
        return "admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "create";
    }

    @PostMapping("/save-user")
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/updateUser")
    public String editUser(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        return "edit-user";
    }

    @PostMapping("/user/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser/{id}")
    public String deleteUser (@PathVariable("id") long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
