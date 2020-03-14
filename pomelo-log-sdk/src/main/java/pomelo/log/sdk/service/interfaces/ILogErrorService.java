package pomelo.log.sdk.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import pomelo.log.sdk.persistence.entity.LogError;

@Transactional(readOnly = true)
public interface ILogErrorService {

	@Transactional(readOnly = false)
	void save(LogError entity);
}
