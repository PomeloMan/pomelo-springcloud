package pomelo.producer.core.module.sys.service.interfaces;

import pomelo.producer.core.module.sys.persistence.entity.User;

public interface IUserService {

	User findOne(String username);
}
