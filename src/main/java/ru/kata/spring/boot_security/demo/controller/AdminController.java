package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserDetailsService;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserDetailsService userDetailsService;

    @Autowired
    public AdminController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        List<User> users = userDetailsService.getAllUsers();
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
        userDetailsService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/updateUser")
    public String editUser(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", userDetailsService.findById(id));
        return "edit-user";
    }

    @PatchMapping("/user/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userDetailsService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser (@PathVariable("id") Long id){
        userDetailsService.deleteUser(id);
        return "redirect:/admin";
    }

}
