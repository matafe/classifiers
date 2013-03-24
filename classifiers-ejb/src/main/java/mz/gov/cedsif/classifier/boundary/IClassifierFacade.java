
package mz.gov.cedsif.classifier.boundary;

import mz.gov.cedsif.classifier.entity.Classifier;

/**
 * Classifier Facade - Interface
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
public interface IClassifierFacade
{
	void create(Classifier classifier) throws Exception;

}