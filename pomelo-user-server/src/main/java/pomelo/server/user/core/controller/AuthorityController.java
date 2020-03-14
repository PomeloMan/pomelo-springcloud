package pomelo.server.user.core.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import pomelo.server.user.core.persistence.entity.Authority;
import pomelo.server.user.core.service.interfaces.IAuthorityService;
import pomelo.server.user.core.view.IAuthority;

@RestController
@RequestMapping("/auth")
@Api(value = "/auth", tags = "AuthorityController")
public class AuthorityController {

	@Autowired
	IAuthorityService authService;

	@PostMapping("query")
	@ApiOperation(value = "获取权限列表", notes = "头部需要带token信息")
	public ResponseEntity<Collection<Authority>> query(
			@RequestBody @ApiParam(name = "object", value = "传入json格式", required = true) IAuthority view) {
		return new ResponseEntity<Collection<Authority>>(authService.query(view), HttpStatus.OK);
	}

	@PostMapping("save")
	@ApiOperation(value = "获取权限列表", notes = "头部需要带token信息")
	public ResponseEntity<Authority> save(
			@ApiParam(name = "object", value = "传入json格式", required = true) IAuthority view) {
		return new ResponseEntity<Authority>(authService.saveOne(view), HttpStatus.OK);
	}
}
