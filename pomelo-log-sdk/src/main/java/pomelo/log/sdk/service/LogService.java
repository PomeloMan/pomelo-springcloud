package pomelo.log.sdk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import pomelo.log.sdk.persistence.entity.Log;
import pomelo.log.sdk.persistence.repo.LogRepository;
import pomelo.log.sdk.service.interfaces.ILogService;

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
