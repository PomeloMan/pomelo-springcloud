package pomelo.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class UriConfiguration {

	private String userServiceUri = "http://127.0.0.1:8100";

	public String getUserServiceUri() {
		return userServiceUri;
	}

	public void setUserServiceUri(String userServiceUri) {
		this.userServiceUri = userServiceUri;
	}
}
