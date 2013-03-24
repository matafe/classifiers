
package mz.gov.cedsif.classifier.util;

import java.util.HashMap;
import java.util.Map;

import mz.gov.cedsif.classifier.entity.Classifier;
import mz.gov.cedsif.classifier.entity.Management;
import mz.gov.cedsif.classifier.entity.ResourceSource;
import mz.gov.cedsif.classifier.entity.Scope;
import mz.gov.cedsif.classifier.entity.Territory;

/**
 * Classifier Factory
 * 
 * @author Mauricio T. Ferraz <matafe@gmail.com>
 */
public abstract class ClassifierFactory
{
	/**
	 * The classifier type mapping.
	 */
	private final static Map<String, Class<? extends Classifier>> TYPE_MAPPING = new HashMap<>();

	static
	{
		TYPE_MAPPING.put(Management.TYPE, Management.class);
		TYPE_MAPPING.put(Scope.TYPE, Scope.class);
		TYPE_MAPPING.put(Territory.TYPE, Territory.class);
		TYPE_MAPPING.put(ResourceSource.TYPE, ResourceSource.class);
		// all types must be put here!
	}

	/**
	 * Create a new instance of the classifier type.
	 * 
	 * @param type The classifier type.
	 * 
	 * @return The new instance of this type.
	 */
	public static Classifier newInstanceForType(final String type)
	{
		try
		{
			return getClassType(type).newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
	}

	private static Class<? extends Classifier> getClassType(final String type)
	{
		Class<? extends Classifier> classType = TYPE_MAPPING.get(type);

		if (classType == null)
		{
			throw new RuntimeException("Classifier Type '"
					+ type
					+ "' not found");
		}
		return classType;
	}

}
