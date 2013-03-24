
package mz.gov.cedsif.classifier.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * This class uses CDI to alias Java EE Web resources, such as the faces
 * context, to CDI beans
 * 
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private FacesContext facesContext;
 * </pre>
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
public class WebResources
{

	@Produces
	@RequestScoped
	public FacesContext produceFacesContext()
	{
		return FacesContext.getCurrentInstance();
	}

}
