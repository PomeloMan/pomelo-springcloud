package pomelo.server.user.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import pomelo.server.user.log.persistence.entity.LogError;
import pomelo.server.user.log.persistence.repo.LogErrorRepository;
import pomelo.server.user.log.service.interfaces.ILogErrorService;

@Service
public class LogErrorService implements ILogErrorService {

	@Autowired
	private LogErrorRepository logErrorRepo;

	@Override
	public void save(LogError entity) {
		Assert.notNull(entity, "");
		logErrorRepo.save(entity);
	}
}
