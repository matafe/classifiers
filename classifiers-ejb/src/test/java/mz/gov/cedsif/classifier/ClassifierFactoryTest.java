
package mz.gov.cedsif.classifier;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import mz.gov.cedsif.classifier.entity.Classifier;
import mz.gov.cedsif.classifier.entity.Management;
import mz.gov.cedsif.classifier.entity.Scope;
import mz.gov.cedsif.classifier.entity.Territory;
import mz.gov.cedsif.classifier.util.ClassifierFactory;

import org.junit.Test;

/**
 * Classifier Factory Unit Test
 * 
 * @author Mauricio T. Ferraz <matafe@gmail.com>
 */
public class ClassifierFactoryTest
{

	@Test
	public void testInstances()
	{
		Classifier mgt = ClassifierFactory.newInstanceForType(Management.TYPE);
		assertEquals(Management.TYPE, mgt.getType());
		assertTrue(mgt instanceof Management);

		Classifier terr = ClassifierFactory.newInstanceForType(Territory.TYPE);
		assertEquals(Territory.TYPE, terr.getType());
		assertTrue(terr instanceof Territory);

		Classifier scope = ClassifierFactory.newInstanceForType(Scope.TYPE);
		assertEquals(Scope.TYPE, scope.getType());
		assertTrue(scope instanceof Scope);
	}

	@Test(expected = RuntimeException.class)
	public void testNonInstances()
	{
		ClassifierFactory.newInstanceForType("unknow");
	}
}
