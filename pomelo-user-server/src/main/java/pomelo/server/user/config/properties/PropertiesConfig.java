package pomelo.server.user.config.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {

	@Bean
	public FileServerProp fileServerProp() {
		return new FileServerProp();
	}
}
