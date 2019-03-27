package pomelo.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * http://localhost:8020/hystrix(http://localhost:8020/turbine.stream)
 */
@SpringBootApplication
@EnableTurbine
@EnableHystrixDashboard
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
