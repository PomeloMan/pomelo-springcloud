package pomelo.server.user.core.persistence.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import pomelo.server.user.core.persistence.entity.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, String>, JpaSpecificationExecutor<Authority> {

}
