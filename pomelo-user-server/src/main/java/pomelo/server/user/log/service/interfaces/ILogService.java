package pomelo.server.user.log.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import pomelo.server.user.log.persistence.entity.Log;

@Transactional(readOnly = true)
public interface ILogService {

	@Transactional(readOnly = false)
	void save(Log log);

}
