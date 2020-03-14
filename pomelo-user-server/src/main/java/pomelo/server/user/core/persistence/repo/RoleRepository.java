package pomelo.server.user.core.persistence.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import pomelo.server.user.core.persistence.entity.Role;

public interface RoleRepository extends CrudRepository<Role, String>, JpaSpecificationExecutor<Role> {

}
