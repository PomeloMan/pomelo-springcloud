package pomelo.server.file.core.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import pomelo.server.file.core.persistence.entity.File;

@Transactional(readOnly = true)
public interface IFileService {

	File findOne(String id);

	@Transactional
	File saveOne(File entity);
}
