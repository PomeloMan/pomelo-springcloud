package pomelo.log.sdk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import pomelo.log.sdk.persistence.entity.LogError;
import pomelo.log.sdk.persistence.repo.LogErrorRepository;
import pomelo.log.sdk.service.interfaces.ILogErrorService;

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
