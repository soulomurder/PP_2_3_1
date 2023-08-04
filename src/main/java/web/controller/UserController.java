//package web.controller;
//
//import web.model.User;
//import web.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public String listUsers(Model model) {
//        model.addAttribute("users", userService.listUsers());
//        return "user_list"; // Это будет имя вашего HTML-шаблона для списка пользователей
//    }
//
//    @GetMapping("/add")
//    public String addUserForm(Model model) {
//        model.addAttribute("user", new User());
//        return "add_user"; // Это будет имя вашего HTML-шаблона для добавления пользователя
//    }
//
//    @PostMapping("/add")
//    public String addUser(@ModelAttribute User user) {
//        userService.add(user);
//        return "redirect:/users"; // Перенаправление на список пользователей после добавления
//    }
//
//    // Реализуйте методы для редактирования и удаления пользователей
//}
package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("ur mom gay");
        model.addAttribute("messages", messages);
        return "index";
    }

}