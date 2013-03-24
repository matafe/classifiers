
package mz.gov.cedsif.classifier.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import mz.gov.cedsif.classifier.boundary.IClassifierQueryFacade;
import mz.gov.cedsif.classifier.entity.Classifier;
import mz.gov.cedsif.classifier.util.ClassifierFactory;

/**
 * This class produces a RESTful service (JAX-RS) that exports the classifier
 * services as REST services.
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
@Path("/classifiers")
@RequestScoped
public class ClassifierResourceRESTService
{
	@Inject
	private IClassifierQueryFacade classifierQuertFacade;

	/**
	 * Find all classifiers
	 * 
	 * @return The classifiers.
	 * 
	 * @throws Exception If something goes wrong.
	 */
	@GET
	@Path("/")
	@Produces({APPLICATION_XML, APPLICATION_JSON})
	public List<Classifier> findAllClassifiers() throws Exception
	{
		return this.classifierQuertFacade.findAllClassifiers();
	}

	/**
	 * Find all classifiers of a type
	 * 
	 * @return The classifiers.
	 * 
	 * @throws Exception If something goes wrong.
	 */
	@SuppressWarnings("unchecked")
	@GET
	@Path("/{type}")
	@Produces({APPLICATION_XML, APPLICATION_JSON})
	public List<Classifier> findAllClassifiersByType(@PathParam("type") final String type)
		throws Exception
	{
		List<? extends Classifier> classifiers = this.classifierQuertFacade
			.findAllClassifiersByType(ClassifierFactory
				.newInstanceForType(type).getClass());
		return (List<Classifier>) classifiers;
	}

	/**
	 * Find a classifier by id.
	 * 
	 * @param id The id.
	 * @return The classifier.
	 * 
	 * @throws Exception If something goes wrong.
	 */
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces({APPLICATION_XML, APPLICATION_JSON})
	public Classifier findClassifierById(@PathParam("id") final Long id)
		throws Exception
	{
		return this.classifierQuertFacade.findClassifierById(id);
	}

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
	@GET
	@Path("/{type}/{code}")
	@Produces({APPLICATION_XML, APPLICATION_JSON})
	public Classifier findClassifierByTypeAndCode(@PathParam("type") final String type,
			@PathParam("code") final String code) throws Exception
	{
		return this.classifierQuertFacade
			.findClassifierByTypeAndCode(ClassifierFactory
				.newInstanceForType(type).getClass(),
					code);
	}
}
