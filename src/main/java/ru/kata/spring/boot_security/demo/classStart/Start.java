package ru.kata.spring.boot_security.demo.classStart;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserDetailsService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
@Component
public class Start {
    private final UserDetailsService userService;
    private final RoleService roleService;

    public Start(UserDetailsService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void startUsers() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);
        User user = new User("Ivan","admin","Pole","admin",32, Arrays.asList(roleAdmin));
        User user1 = new User("Victor","user","Don","user",19,Arrays.asList(roleUser));
        User user2 = new User("Polina","polina","Moroz","polina",26,Arrays.asList(roleUser));
        userService.saveUser(user);
        userService.saveUser(user1);
        userService.saveUser(user2);
    }
}
