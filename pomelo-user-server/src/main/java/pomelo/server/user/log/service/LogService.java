package pomelo.server.user.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import pomelo.server.user.log.persistence.entity.Log;
import pomelo.server.user.log.persistence.repo.LogRepository;
import pomelo.server.user.log.service.interfaces.ILogService;

@Service
public class LogService implements ILogService {

	@Autowired
	private LogRepository logRepo;

	@Override
	public void save(Log entity) {
		Assert.notNull(entity, "");
		logRepo.save(entity);
	}
}
