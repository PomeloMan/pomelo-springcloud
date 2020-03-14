package pomelo.server.user.log.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import pomelo.server.user.log.persistence.entity.LogError;

@Transactional(readOnly = true)
public interface ILogErrorService {

	@Transactional(readOnly = false)
	void save(LogError entity);
}
