package pomelo.log.sdk.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import pomelo.log.sdk.persistence.entity.Log;

@Transactional(readOnly = true)
public interface ILogService {

	@Transactional(readOnly = false)
	void save(Log log);

}
