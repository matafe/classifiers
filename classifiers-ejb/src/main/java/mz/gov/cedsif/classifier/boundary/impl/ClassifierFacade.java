
package mz.gov.cedsif.classifier.boundary.impl;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import mz.gov.cedsif.classifier.boundary.IClassifierFacade;
import mz.gov.cedsif.classifier.data.IClassifierRepository;
import mz.gov.cedsif.classifier.entity.Classifier;
import mz.gov.cedsif.util.Boundary;

/**
 * Classifier Facade - EJB Session Bean Implementation
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
@Stateless
@Boundary(documentationLink = "http://www.cedsif.gov.mz", responsibility = "Classifiers Maintenance")
public class ClassifierFacade
	implements IClassifierFacade
{

	@Inject
	private Logger log;

	@Inject
	private IClassifierRepository classifierRepository;

	@Inject
	private Event<Classifier> classifierEvent;

	@Override
	public void create(Classifier classifier) throws Exception
	{
		log.info("Criando Classificador: '"
				+ classifier.getType()
				+ "' : '"
				+ classifier.getCode()
				+ "'");
		classifierRepository.create(classifier);
		classifierEvent.fire(classifier);
	}
}
