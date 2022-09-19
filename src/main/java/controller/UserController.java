package controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bo.UserBo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import payload.UserPayload;
import services.UserService;
import java.util.*;

@RestController
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserController {

	private UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserBo> getAllUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping(value = "/users/{id}", produces = "application/json", method = RequestMethod.GET)
	public UserBo getUser(@PathVariable("id") int userId) {
		return userService.getUser(userId);
	}

	@PostMapping("/users")
	public Integer addUser(@RequestBody UserPayload user) {
		return userService.addUser(user);

	}

	@RequestMapping(value = "/users/{id}", produces = "application/json", consumes = "application/json", method = RequestMethod.PUT)
	public UserBo updateUser(@RequestBody UserPayload user, @PathVariable("id") int userId) {
		return userService.updateUser(user, userId);

	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable("id") int userId) {
		userService.deleteUser(userId);
		return "User has been Deleted";
	}

}
