package pomelo.consumer.feign.callback;

import pomelo.consumer.core.module.sys.persistence.entity.User;
import pomelo.consumer.feign.ProducerFeignClient;

public class ProducerFeignClientFallback implements ProducerFeignClient {

	@Override
	public User findUser(String username) {
		return new User(username, -100);
	}

}
