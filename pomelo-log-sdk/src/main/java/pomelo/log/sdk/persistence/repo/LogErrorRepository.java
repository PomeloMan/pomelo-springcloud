package pomelo.log.sdk.persistence.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import pomelo.log.sdk.persistence.entity.LogError;

public interface LogErrorRepository extends CrudRepository<LogError, String>, JpaSpecificationExecutor<LogError> {

}
