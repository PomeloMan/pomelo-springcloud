package pomelo.server.file.core.persistence.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import pomelo.server.file.core.persistence.entity.File;

public interface FileRepository extends CrudRepository<File, String>, JpaSpecificationExecutor<File> {

}
