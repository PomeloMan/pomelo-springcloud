package pomelo.producer.core.module.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pomelo.producer.core.module.sys.persistence.entity.User;
import pomelo.producer.core.module.sys.service.interfaces.IUserService;

@RestController("userController")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("/{username}")
	public ResponseEntity<User> info(@PathVariable String username) {
		return new ResponseEntity<>(userService.findOne(username), HttpStatus.OK);
	}
}
