
package mz.gov.cedsif.classifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import mz.gov.cedsif.classifier.boundary.IClassifierFacade;
import mz.gov.cedsif.classifier.boundary.IClassifierQueryFacade;
import mz.gov.cedsif.classifier.entity.Classifier;
import mz.gov.cedsif.classifier.entity.Management;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ClassifierIntegTest
{
	@Deployment
	public static Archive<?> createTestArchive()
	{
		WebArchive war = ShrinkWrap
			.create(WebArchive.class, "test.war")
			.addPackage("mz.gov.cedsif.classifier.data")
			.addPackage("mz.gov.cedsif.classifier.entity")
			.addPackage("mz.gov.cedsif.classifier.boundary")
			.addPackage("mz.gov.cedsif.classifier.boundary.impl")
			.addPackage("mz.gov.cedsif.classifier.kernel")
			.addPackage("mz.gov.cedsif.classifier.util")
			.addPackage("mz.gov.cedsif.util")
			.addAsResource("META-INF/test-persistence.xml",
					"META-INF/persistence.xml")
			.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
			// Deploy our test datasource
			.addAsWebInfResource("test-ds.xml", "test-ds.xml");
		// System.out.println(war.toString(true));
		return war;
	}

	@Inject
	private IClassifierFacade facade;

	@Inject
	private IClassifierQueryFacade queryFacade;

	@Inject
	private Logger log;

	@Test
	public void testCreateManagament() throws Exception
	{
		Management mgt = new Management();
		mgt.setCode("1000110000G000000000");
		mgt.setDescription("Gestao Maputo");

		this.facade.create(mgt);

		assertNotNull(mgt.getId());

		log.info("The Classifier of type '"
				+ mgt.getType()
				+ "' with code '"
				+ mgt.getCode()
				+ "' was persisted with id "
				+ mgt.getId());
	}

	@Test
	public void testFindAll() throws Exception
	{
		List<Classifier> classifiers = this.queryFacade.findAllClassifiers();
		assertNotNull(classifiers);
		System.out.println("#################");
		System.out.println(classifiers.size());
		System.out.println("#################");
	}

	@Test
	public void testFindAllByType() throws Exception
	{
		List<Management> classifiers = this.queryFacade
			.findAllClassifiersByType(Management.class);
		assertNotNull(classifiers);
		System.out.println("#################");
		System.out.println(classifiers.size());
		System.out.println("#################");
	}

	@Test
	public void testFindByTypeAndCode() throws Exception
	{
		Management classifier = this.queryFacade
			.findClassifierByTypeAndCode(Management.class,
					"1000110000G000000000");
		assertNotNull(classifier);
		System.out.println("#################");
		System.out.println(classifier);
		System.out.println("#################");
	}

	@Test
	public void testFindId() throws Exception
	{
		Management mgt = new Management();
		mgt.setCode("1000000000G000000000");
		mgt.setDescription("Gestao Central");

		this.facade.create(mgt);

		assertNotNull(mgt.getId());

		Classifier classifier = this.queryFacade
			.findClassifierById(mgt.getId());
		assertNotNull(classifier);
		assertEquals(mgt.getId(), classifier.getId());
		assertEquals(mgt, classifier);
		System.out.println("#################");
		System.out.println(classifier);
		System.out.println("#################");
	}
}
