
package mz.gov.cedsif.classifier.util;

/**
 * Represents a Classifiers
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
public interface IClassifier
{
	/**
	 * Get the id.
	 * 
	 * @return the id.
	 */
	Long getId();

	/**
	 * Get the code
	 * 
	 * @return the code.
	 */
	String getCode();

	/**
	 * Get the description.
	 * 
	 * @return the description.
	 */
	String getDescription();

	/**
	 * Get the type.
	 * 
	 * @return the type.
	 */
	String getType();
}
