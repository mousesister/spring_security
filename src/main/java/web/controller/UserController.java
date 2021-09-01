package web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
	private final UserDao userDao;
	private final RoleDao roleDao;

	public UserController(UserDao userDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
	}

	@GetMapping("/")
	public String main() {
		return "index";
	}

	@GetMapping("/user")
	public String userPage(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("currentUserRoles", user.getRoles()
				.toString()
				.replace("[", "")
				.replace("ROLE_","")
				.replace("]",""));
		return "user";
	}


	@GetMapping("hello")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}

	@GetMapping("login")
	public String loginPage() {
		return "login";
	}

}