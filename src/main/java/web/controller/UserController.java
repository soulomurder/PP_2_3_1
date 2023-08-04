package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/read")
    public String readUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "read";
    }

    @GetMapping("/create")
    public String createUserPage() {
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("email") String email) {
        userService.createUser(new User(firstName, lastName, email));
        return "redirect:/read";
    }

    @GetMapping("/update")
    public String updateUserPage() {
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam("email") String email) {
        if (userService.getUserById(id) != null) {
            userService.editEmail(id, email);
        }
        return "redirect:/read";
    }

    @GetMapping("/drop")
    public String dropUserPage() {
        return "drop";
    }

    @PostMapping("/drop")
    public String dropUser(@RequestParam("id") Long id) {
        if (userService.getUserById(id) != null) {
            userService.dropUser(id);
        }
        return "redirect:/read";
    }
}
