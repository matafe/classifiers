
package mz.gov.cedsif.classifier.boundary.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mz.gov.cedsif.classifier.boundary.IClassifierQueryFacade;
import mz.gov.cedsif.classifier.data.IClassifierRepository;
import mz.gov.cedsif.classifier.entity.Classifier;
import mz.gov.cedsif.util.Boundary;

/**
 * Classifier Query Facade - EJB Session Bean Implementation
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
@Stateless
@Boundary(documentationLink = "http://www.cedsif.gov.mz", responsibility = "Find the Classifiers")
public class ClassifierQueryFacadeBean
	implements IClassifierQueryFacade
{
	@Inject
	private Logger log;

	@Inject
	private IClassifierRepository classifierRepository;

	@Override
	public Classifier findClassifierById(final Long id) throws Exception
	{
		log.info("Buscando Classificador por ID: '" + id + "'");
		return this.classifierRepository.findById(id);
	}

	@Override
	public List<Classifier> findAllClassifiers() throws Exception
	{
		log.info("Buscando Todos os Classificadores");
		return this.classifierRepository.findAll();
	}

	@Override
	public <T extends Classifier> T findClassifierByTypeAndCode(final Class<T> type,
			final String code) throws Exception
	{
		log.info("Buscando Classificador por Tipo: '"
				+ type
				+ "' e Codigo: '"
				+ code
				+ "'");
		return this.classifierRepository.findByTypeAndCode(type, code);
	}

	@Override
	public List<String> findAllClassifierTypes()
	{
		return this.classifierRepository.findAllTypes();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Classifier> List<T> findAllClassifiersByType(final Class<T> type)
		throws Exception
	{
		return this.classifierRepository.findAllByType(type);
	}
}
