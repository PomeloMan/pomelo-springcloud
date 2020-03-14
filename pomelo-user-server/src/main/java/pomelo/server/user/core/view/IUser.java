package pomelo.server.user.core.view;

import io.swagger.annotations.ApiModel;
import pomelo.server.user.core.persistence.entity.User;

@ApiModel
public class IUser extends User {

	private static final long serialVersionUID = 1L;

	private String search;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
