package pomelo.server.user.core.view;

import pomelo.server.user.core.persistence.entity.Authority;

public class IAuthority extends Authority {

	private static final long serialVersionUID = 1L;

	private String search;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	// 状态信息
	public String getStatusDesc() {
		return this.status.getDescription();
	}

	public int getStatusCode() {
		return this.status.getCode();
	}
}
