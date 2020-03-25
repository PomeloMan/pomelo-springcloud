package pomelo.server.user.core.persistence.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pomelo.server.user.core.enums.Status;
import pomelo.server.user.core.persistence.entity.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, String>, JpaSpecificationExecutor<Authority> {

	@Modifying
	@Query(value = "update Authority a set a.status = :status where a.name in :ids")
	void deleteByIds(@Param("ids") Collection<String> ids, @Param("status") Status status);

}
