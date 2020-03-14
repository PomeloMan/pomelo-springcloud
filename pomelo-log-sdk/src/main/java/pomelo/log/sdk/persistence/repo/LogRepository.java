package pomelo.log.sdk.persistence.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import pomelo.log.sdk.persistence.entity.Log;

public interface LogRepository extends CrudRepository<Log, String>, JpaSpecificationExecutor<Log> {

}
