package pomelo.server.user.core.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import pomelo.server.user.core.persistence.entity.Authority;
import pomelo.server.user.core.persistence.repo.AuthorityRepository;
import pomelo.server.user.core.service.interfaces.IAuthorityService;
import pomelo.server.user.core.utils.BeanUtils;
import pomelo.server.user.core.utils.DateUtil;
import pomelo.server.user.core.view.IAuthority;
import pomelo.server.user.core.view.IPage;

@Service
public class AuthorityService implements IAuthorityService {

	private final Log logger = LogFactory.getLog(AuthorityService.class);

	@Autowired
	AuthorityRepository authorityRep;

	private Specification<Authority> getQueryClause(IAuthority view) {
		return new Specification<Authority>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Authority> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

				if (view == null) {
					if (logger.isDebugEnabled()) {
						logger.debug(view);
					}
					return null;
				}

				String search = view.getSearch();

				String name = null;
				Authority authority = BeanUtils.transform(view, Authority.class);
				if (authority != null) {
					name = authority.getName();
				}

				List<Predicate> restrictions = new ArrayList<Predicate>();
				if (StringUtils.isNotEmpty(search)) {
					Predicate fuzzyPredicate = null;
					try {
						fuzzyPredicate = builder.equal(root.get("createdDate"),
								DateUtils.parseDate(search, DateUtil.YYYY_MM_DD));
					} catch (ParseException e) {
						fuzzyPredicate = builder.like(root.get("name"), "%" + search + "%");
					}
					restrictions.add(fuzzyPredicate);
				}

				if (StringUtils.isNotEmpty(name)) {
					Predicate likePredicate = builder.like(root.get("name"), "%" + name + "%");
					restrictions.add(likePredicate);
				}

				query.where(builder.and(restrictions.toArray(new Predicate[restrictions.size()])));
				return query.getGroupRestriction();
			}
		};
	}

	@Override
	public Collection<Authority> query(IAuthority view) {
		return authorityRep.findAll(getQueryClause(view));
	}

	@Override
	public Page<Authority> query(IPage<IAuthority> pageView, Pageable pageable) {
		if (pageable == null) {
			pageable = pageView.getPageable();
		}
		return authorityRep.findAll(getQueryClause(pageView.getObject()), pageable);
	}

	@Override
	public Authority saveOne(IAuthority view) {
		return this.saveOne(BeanUtils.transform(view, Authority.class));
	}

	@Override
	public Authority saveOne(Authority entity) {
		Assert.notNull(entity, "");
		Assert.notNull(entity.getName(), "");
		Authority _entity = authorityRep.findById(entity.getName()).orElse(null);
		if (_entity != null) {
			entity.setVersion(_entity.getVersion());
		}
		return authorityRep.save(entity);
	}

	@Override
	public Collection<Authority> save(Collection<Authority> entities) {
		List<Authority> result = new ArrayList<Authority>();
		entities.stream().forEach(entity -> result.add(saveOne(entity)));
		return result;
	}

}
