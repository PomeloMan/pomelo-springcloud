package pomelo.server.user.core.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import pomelo.server.user.core.persistence.entity.User;
import pomelo.server.user.core.service.interfaces.IUserService;
import pomelo.server.user.core.view.IPage;
import pomelo.server.user.core.view.IUser;

@RestController
@RequestMapping("/user")
@Api(value = "/user", tags = "UserController")
//@CrossOrigin(origins = "http://127.0.0.1", maxAge = 3600)
public class UserController {

	@Autowired
	IUserService userService;

	@PostMapping("list")
	@ApiOperation(value = "用户列表")
	public ResponseEntity<Collection<User>> list(@RequestBody IUser view) {
		return new ResponseEntity<Collection<User>>(userService.query(view), HttpStatus.OK);
	}

	@PostMapping("page")
	@ApiOperation(value = "用户分页")
	public ResponseEntity<Page<IUser>> page(@RequestBody IPage<IUser> pageView) {
		return new ResponseEntity<Page<IUser>>(
				userService.query(pageView, pageView.getPageable(Direction.ASC, "username")), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "用户详情")
	public ResponseEntity<IUser> info(@PathVariable String id) {
		return new ResponseEntity<IUser>(userService.findOne(id), HttpStatus.OK);
	}

	@PostMapping()
	@ApiOperation(value = "新建用戶", notes = "头部需要带token信息")
	public ResponseEntity<User> save(@ApiParam(required = true) @RequestBody IUser view) {
		return new ResponseEntity<User>(userService.saveOne(view), HttpStatus.OK);
	}

	@DeleteMapping()
	@ApiOperation(value = "删除角色", notes = "头部需要带token信息")
	public ResponseEntity<User> delete(@ApiParam(required = true) @RequestBody List<String> ids) {
		userService.delete(ids);
		return new ResponseEntity<User>(HttpStatus.OK);
	}

	@PutMapping()
	@ApiOperation(value = "修改角色", notes = "头部需要带token信息")
	public ResponseEntity<User> update(@ApiParam(required = true) @RequestBody IUser view) {
		return new ResponseEntity<User>(userService.saveOne(view), HttpStatus.OK);
	}
}
