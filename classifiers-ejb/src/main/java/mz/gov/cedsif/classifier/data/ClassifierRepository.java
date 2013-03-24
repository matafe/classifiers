
package mz.gov.cedsif.classifier.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import mz.gov.cedsif.classifier.entity.Classifier;

/**
 * Classifier Repository - Implementation
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
@ApplicationScoped
public class ClassifierRepository
	implements IClassifierRepository
{
	@Inject
	private EntityManager em;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(final Classifier classifier)
	{
		this.em.persist(classifier);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Classifier> findAll()
	{
		return em.createNamedQuery(Classifier.FIND_ALL, Classifier.class)
			.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Classifier findById(final Long id)
	{
		return em.find(Classifier.class, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> findAllTypes()
	{
		return em.createNamedQuery(Classifier.FIND_ALL_TYPES, String.class)
			.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Classifier> List<T> findAllByType(final Class<T> type)
	{
		return em.createQuery("FROM " + type.getSimpleName(), type)
			.setHint("org.hibernate.cacheable", "true").getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Classifier> T findByTypeAndCode(Class<T> type, String code)
	{
		return em
			.createQuery("FROM "
					+ type.getSimpleName()
					+ " c WHERE c.code = :code",
					type).setHint("org.hibernate.cacheable", "true")
			.setParameter("code", code).getSingleResult();
	}
}
