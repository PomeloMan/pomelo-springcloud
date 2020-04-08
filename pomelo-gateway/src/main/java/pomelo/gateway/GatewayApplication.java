package pomelo.gateway;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import pomelo.gateway.config.UriConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(UriConfiguration.class)
public class GatewayApplication {

	private final Log logger = LogFactory.getLog(GatewayApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
		String usUri = uriConfiguration.getUserServiceUri();
		logger.info(usUri);
		return builder.routes().route(p -> p.path("/get").filters(f -> f.addRequestHeader("Hello", "World")).uri(usUri))
				.build();
	}

}
