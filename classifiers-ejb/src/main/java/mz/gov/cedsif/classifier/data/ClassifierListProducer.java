
package mz.gov.cedsif.classifier.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import mz.gov.cedsif.classifier.boundary.IClassifierQueryFacade;
import mz.gov.cedsif.classifier.entity.Classifier;

/**
 * Classifier List Produces
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
@RequestScoped
public class ClassifierListProducer
{
	@Inject
	private IClassifierQueryFacade classifierQueryFacade;

	private List<Classifier> classifiers;

	//@Produces
	//@Named
	public List<Classifier> getClassifiers()
	{
		return classifiers;
	}

	public void onClassifierListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Classifier classifier)
		throws Exception
	{
		// findAllClassifiers();
		System.out.println("ClassifierListProducer.onClassifierListChanged");
	}

	@PostConstruct
	public void findAllClassifiers() throws Exception
	{
		//this.classifiers = this.classifierQueryFacade.findAllClassifiers();
	}
}
