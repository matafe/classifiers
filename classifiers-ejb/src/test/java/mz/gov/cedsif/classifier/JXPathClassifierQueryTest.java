
package mz.gov.cedsif.classifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mz.gov.cedsif.classifier.entity.Classifier;
import mz.gov.cedsif.classifier.entity.Management;
import mz.gov.cedsif.classifier.entity.Scope;
import mz.gov.cedsif.classifier.entity.Territory;
import mz.gov.cedsif.classifier.kernel.XPathEvaluator;

import org.apache.commons.jxpath.JXPathContext;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * JXPath Classifier Query Unit Test
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
public class JXPathClassifierQueryTest
{

	private Management management;

	private Collection<Classifier> classifiers;

	private Collection<Management> managements;

	private Collection<Scope> scopes;

	private Collection<Territory> territories;

	@Before
	public void init()
	{
		this.classifiers = new ArrayList<>();

		populateManagements();
		populateScopes();
		populateTerritories();

		assertNotNull(this.management);

		assertEquals(12, this.managements.size());
		assertEquals(5, this.scopes.size());
		assertEquals(2, this.territories.size());

		assertEquals(this.managements.size()
				+ this.scopes.size()
				+ this.territories.size(), classifiers.size());

	}

	private void populateScopes()
	{
		this.scopes = new ArrayList<>();

		for (int i = 0; i < 5; i++)
		{
			Scope scope = new Scope();
			scope.setId(Long.valueOf(i));
			scope.setCode(String.format("%04d", i));
			scope.setDescription("Description for " + scope.getCode());
			scope.setRemarks("Remarks for" + scope.getCode());

			this.scopes.add(scope);
		}
		this.classifiers.addAll(scopes);
	}

	private void populateTerritories()
	{
		this.territories = new ArrayList<>();

		for (int i = 0; i < 2; i++)
		{
			Territory terr = new Territory();
			terr.setId(Long.valueOf(i));
			terr.setCode(String.format("%010d", i));
			terr.setDescription("Description for " + terr.getCode());
			terr.setRemarks("Remarks for" + terr.getCode());

			this.territories.add(terr);
		}

		this.classifiers.addAll(territories);
	}

	private void populateManagements()
	{
		this.managements = new ArrayList<>();

		for (int i = 0; i < 12; i++)
		{
			Management mgt = new Management();
			mgt.setId(Long.valueOf(i));
			mgt.setCode("1000" + String.format("%02d", i) + "0000G000000000 ");
			mgt.setDescription("Description for " + mgt.getCode());
			mgt.setRemarks("Remarks for" + mgt.getCode());

			this.managements.add(mgt);

			if (i == 1)
			{
				this.management = mgt;
			}
		}

		this.classifiers.addAll(managements);
	}

	@Test
	public void testGetManagement()
	{
		JXPathContext context = JXPathContext.newContext(management);
		Classifier value = (Classifier) context.getValue(".", Classifier.class);
		System.out.println(value);
		assertNotNull(value);
	}

	@Test
	public void testGetManagementCode()
	{
		Object value = JXPathContext.newContext(management).getValue("/code");
		System.out.println(value);
		assertNotNull(value);
	}

	@Test
	public void testGetManagementDescription()
	{
		Object value = JXPathContext.newContext(management)
			.getValue("/description");
		System.out.println(value);
		assertNotNull(value);
	}

	@Test
	public void testGetClassifiers()
	{
		JXPathContext context = JXPathContext.newContext(managements);
		@SuppressWarnings("unchecked")
		Collection<Classifier> value = (Collection<Classifier>) context
			.getValue(".");
		System.out.println(value);
		assertNotNull(value);
	}

	@Ignore
	@Test
	public void testGetManagementsClassifiers()
	{
		Map<String, Collection<Classifier>> map = new HashMap<>();
		map.put("classifiers", classifiers);
		JXPathContext context = JXPathContext.newContext(map);
		@SuppressWarnings({"unchecked"})
		Iterator<Object> iterator = context
			.iterate("/classifiers + [contains(@code, '000')]");
		while (iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
		// System.out.println(context.getValue("id=0"));
	}

	// [contains(@code, '" + classifierCode + "')]

	// "[codeLevel1='" + ugeOrgan + "']"));

	// "[codeLevel1='"
	// + provincePartCode
	// + "' and codeLevel2='"
	// + districtPartCode
	// + "' and code !='"
	// + provincePartCode
	// + districtPartCode
	// + "000000"
	// + "']");
	@Test
	public void testGetManagementsByExpressionEmpty()
	{
		Collection<Classifier> founds = XPathEvaluator.getInstance()
			.evaluate(classifiers, Management.TYPE, "");
		for (Classifier classifier : founds)
		{
			System.out.println(classifier);
		}
	}

}