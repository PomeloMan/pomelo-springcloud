package pomelo.producer.core.module.sys.persistence.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import pomelo.producer.core.module.sys.persistence.entity.User;

public interface UserRepository extends CrudRepository<User, String>, JpaSpecificationExecutor<User> {

}
