package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String list(ModelMap model) {
        model.addAttribute("userList", userService.getAll());

        return "users/list";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.getById(id));

        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable Long id) {
        userService.update(user);

        return "redirect:/users/list";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);

        return "redirect:/users/list";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());

        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.create(user);

        return "redirect:/users/list";
    }
}
