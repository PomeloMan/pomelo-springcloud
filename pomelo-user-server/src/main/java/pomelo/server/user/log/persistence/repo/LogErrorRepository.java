package pomelo.server.user.log.persistence.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import pomelo.server.user.log.persistence.entity.LogError;

public interface LogErrorRepository extends CrudRepository<LogError, String>, JpaSpecificationExecutor<LogError> {

}
