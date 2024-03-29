
package mz.gov.cedsif.classifier.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * <p>
 * JAX-RS Activator
 * 
 * <p>A class extending {@link Application} and annotated with @ApplicationPath is
 * the Java EE 6 "no XML" approach to activating JAX-RS.
 * 
 * <p>
 * Resources are served relative to the servlet path specified in the
 * {@link ApplicationPath} annotation.
 * </p>
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
@ApplicationPath("/rest")
public class JaxRsActivator
	extends Application
{
	/* class body intentionally left blank */
}
