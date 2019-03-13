package pomelo.producer.core.module.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pomelo.producer.core.module.sys.persistence.entity.User;
import pomelo.producer.core.module.sys.persistence.repo.UserRepository;
import pomelo.producer.core.module.sys.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User findOne(String username) {
		return userRepo.findById(username).get();
	}

}
