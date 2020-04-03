package pomelo.server.user.core.view;

import java.util.Collection;

import io.swagger.annotations.ApiModel;
import pomelo.server.user.core.persistence.entity.Authority;
import pomelo.server.user.core.persistence.entity.Role;
import pomelo.server.user.core.persistence.entity.User;

@ApiModel
public class IUser extends User {

	private static final long serialVersionUID = 1L;

	private String search;
	private Collection<Role> roles;
	private Collection<Authority> authorities;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Collection<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<Authority> authorities) {
		this.authorities = authorities;
	}

}
