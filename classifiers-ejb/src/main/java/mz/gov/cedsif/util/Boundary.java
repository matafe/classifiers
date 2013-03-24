
package mz.gov.cedsif.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;


/**
 * Defines a Boundary
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
@Stereotype
@SLAAudit
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Boundary
{
	/**
	 * A pointer to existing documentation, user story or use case.
	 */
	String documentationLink() default "";

	/**
	 * The essential intension and responsibility of the Boundary
	 */
	String responsibility();
}
