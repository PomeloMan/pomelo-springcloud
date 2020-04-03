package pomelo.server.user.core.service.interfaces;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import pomelo.server.user.core.persistence.entity.Role;
import pomelo.server.user.core.view.IPage;
import pomelo.server.user.core.view.IRole;

/**
 * @ClassName IRoleService.java
 * @Description TODO
 * @author PomeloMan
 */
@Transactional(readOnly = true)
public interface IRoleService {

	/**
	 * @param id
	 * @return
	 */
	IRole findOne(String id);

	/**
	 * @param view
	 * @return
	 */
	Collection<IRole> query(IRole view);

	/**
	 * @param view
	 * @param pageable
	 * @return
	 */
	Page<IRole> query(IPage<IRole> pageView, Pageable pageable);

	/**
	 * @param view
	 * @return
	 */
	@Transactional
	Role saveOne(IRole view);

	/**
	 * @param role
	 * @return
	 */
	@Transactional
	Role saveOne(Role entity);

	/**
	 * @param roles
	 * @return
	 */
	@Transactional
	Collection<Role> save(Collection<Role> entities);

	/**
	 * @param ids
	 * @return
	 */
	@Transactional
	void delete(Collection<String> ids);
}
