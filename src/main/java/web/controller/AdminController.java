package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {
    private final UserDao userDao;
    private final RoleDao roleDao;

    public AdminController(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }
    @GetMapping(value = "/admin")
    public String index(Model model) {
        model.addAttribute("list", userDao.index());
        return "admin";
    }
    @GetMapping("/{count}")
    public String show(@PathVariable("count") int count, Model model) {
        model.addAttribute("user", userDao.show(count));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleDao.getAllRoles());
        return "new";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "checkbox_roles", required = false) String[] checkboxRoles) {
        Set<Role> roles = new HashSet<>();
        for (String role : checkboxRoles) {
            roles.add(roleDao.getRoleByName(role));
        }

        user.setRoles(roles);
        userDao.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{count}/edit")
    public String edit(@PathVariable("count") int count, Model model) {
        model.addAttribute("user", userDao.show(count));
        model.addAttribute("roles", roleDao.getAllRoles());
        return "/edit";
    }
    @PostMapping("/{count}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("count") int count,
                         @RequestParam(value = "checkbox_roles", required = false) String[] checkboxRoles) {
        Set<Role> roles = new HashSet<>();
        for (String role : checkboxRoles) {
            roles.add(roleDao.getRoleByName(role));
        }

        user.setRoles(roles);
        userDao.update(user);
        return "redirect:/admin";
    }
    @PostMapping("/{count}/delete")
    public String delete(@PathVariable("count") int count) {
        userDao.delete(count);
        return "redirect:/admin";
    }
}
