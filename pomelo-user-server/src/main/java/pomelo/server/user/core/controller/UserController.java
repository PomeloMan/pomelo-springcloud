package pomelo.server.user.core.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pomelo.server.user.core.persistence.entity.User;
import pomelo.server.user.core.service.interfaces.IUserService;
import pomelo.server.user.core.view.IPage;
import pomelo.server.user.core.view.IUser;
import pomelo.server.user.log.annotation.LogOperation;

@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = "http://127.0.0.1", maxAge = 3600)
@Api(value = "/user", tags = "UserController")
public class UserController {

	@Autowired
	IUserService userService;

	/**
	 * @param principal
	 * @param user {@link CurrentUserHandlerMethodArgumentResolver.class} 自定义注解方法参数，返回处理后的数据
	 * @return
	 */
	@GetMapping("/me")
	@ApiOperation(value = "me")
	public Principal user(Principal principal, User user) {
		return principal;
	}

	/**
	 * @param iuser
	 * @return
	 */
	@PostMapping("list")
	@ApiOperation(value = "list")
	public ResponseEntity<Collection<User>> list(
			@RequestBody IUser view) {
		return new ResponseEntity<Collection<User>>(userService.query(view), HttpStatus.OK);
	}

	/**
	 * @param view
	 * @return
	 * @PreAuthorize.hasPermission {@link pomelo.core.configure.authentication.DefaultPermissionEvaluator.java}
	 */
	@PostMapping("page")
	@ApiOperation(value = "page")
	public ResponseEntity<Page<User>> page(@RequestBody IPage<IUser> pageView) {
		return new ResponseEntity<Page<User>>(userService.query(pageView, null), HttpStatus.OK);
	}

	@PostMapping("/save")
	@LogOperation("save")
	@ApiOperation(value = "save")
	public ResponseEntity<User> save(@RequestBody IUser view) {
		return new ResponseEntity<User>(userService.saveOne(view), HttpStatus.OK);
	}

}
