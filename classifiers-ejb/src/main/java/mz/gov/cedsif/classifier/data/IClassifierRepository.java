
package mz.gov.cedsif.classifier.data;

import java.util.List;

import mz.gov.cedsif.classifier.entity.Classifier;

/**
 * Classifier Repository - Interface
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
public interface IClassifierRepository
{
	/**
	 * Creates a classifier.
	 * 
	 * @param classifier The classifier.
	 */
	void create(Classifier classifier);

	/**
	 * Find all classifiers.
	 * 
	 * @return The classifiers.
	 */
	List<Classifier> findAll();

	/**
	 * Find all classifiers of a specific type.
	 * 
	 * @param type The classifier type.
	 * 
	 * @return The classifiers.
	 */
	<T extends Classifier> List<T> findAllByType(Class<T> type);

	/**
	 * Find all classifiers types.
	 * 
	 * @return The classifier types
	 */
	List<String> findAllTypes();

	/**
	 * Find a classifier by id.
	 * 
	 * @param id The classifier id.
	 * 
	 * @return The classifier.
	 */
	Classifier findById(Long id);

	/**
	 * Find a classifier by type and code.
	 * 
	 * @param type The classifier type.
	 * @param code The classifier code.
	 * 
	 * @return The classifier
	 */
	<T extends Classifier> T findByTypeAndCode(Class<T> type, String code);
}