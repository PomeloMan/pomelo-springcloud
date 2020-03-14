package pomelo.server.user.log.persistence.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import pomelo.server.user.log.persistence.entity.Log;

public interface LogRepository extends CrudRepository<Log, String>, JpaSpecificationExecutor<Log> {

}
