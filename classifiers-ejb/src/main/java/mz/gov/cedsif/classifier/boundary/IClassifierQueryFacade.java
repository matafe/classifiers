
package mz.gov.cedsif.classifier.boundary;

import java.util.List;

import mz.gov.cedsif.classifier.entity.Classifier;

/**
 * Classifier Query Facade - Interface
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
public interface IClassifierQueryFacade
{
	/**
	 * Find a classifier by id.
	 * 
	 * @param id The id.
	 * @return The classifier.
	 * 
	 * @throws Exception If something goes wrong.
	 */
	Classifier findClassifierById(Long id) throws Exception;

	/**
	 * Find the classifier by type and code.
	 * 
	 * @param type The classifier type.
	 * @param code The classifier code
	 * 
	 * @return The classifier
	 * 
	 * @throws Exception If something goes wrong.
	 */
	<T extends Classifier> T findClassifierByTypeAndCode(Class<T> type,
			String code) throws Exception;

	/**
	 * Find all classifiers
	 * 
	 * @return The classifiers.
	 * 
	 * @throws Exception If something goes wrong.
	 */
	List<Classifier> findAllClassifiers() throws Exception;

	/**
	 * Find all classifiers of a specific type.
	 * 
	 * @param The classifier type.
	 * 
	 * @return The classifiers.
	 * 
	 * @throws Exception If something goes wrong.
	 */
	<T extends Classifier> List<T> findAllClassifiersByType(Class<T> type)
		throws Exception;

	/**
	 * Find all classifier types.
	 * 
	 * @return The classifier types.
	 */
	List<String> findAllClassifierTypes();

}