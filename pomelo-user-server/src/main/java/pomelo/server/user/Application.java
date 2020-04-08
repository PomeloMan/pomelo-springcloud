package pomelo.server.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 人员服务（用户，角色，菜单）
 */
//@EnableDiscoveryClienty
@SpringBootApplication(scanBasePackages = { "pomelo.server.user" })
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
//		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
	}
}
