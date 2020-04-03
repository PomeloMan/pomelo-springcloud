package pomelo.server.user.core.persistence.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pomelo.server.user.core.enums.Status;
import pomelo.server.user.core.persistence.entity.Role;

public interface RoleRepository extends CrudRepository<Role, String>, JpaSpecificationExecutor<Role> {

	@Modifying
	@Query(value = "update Role r set r.status = :status where r.name in :ids")
	void updateStatusByIds(@Param("ids") Collection<String> ids, @Param("status") Status status);
}
