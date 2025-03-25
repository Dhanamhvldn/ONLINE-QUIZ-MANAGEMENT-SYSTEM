package org.OnlineQuizApplication.Controller;

import org.OnlineQuizApplication.Entity.Users;
import org.OnlineQuizApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/addPassword")
	public String savePassword(@RequestBody Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.createUser(user);
		return "User added successsfully";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return"login";
	}
	@GetMapping("/user")
	public String studentDashBoard() {
		return"user";
	}
	@GetMapping("/admin")
public String teacherDashBoard() {
	return"admin";
}

}
