package pomelo.consumer.feign;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pomelo.consumer.core.module.sys.persistence.entity.User;

public interface ProducerFeignClient {

	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
	public User findUser(@PathVariable("username") String username);
}
