
package mz.gov.cedsif.util;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * Service Level Agreement for Auditing.
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
@Inherited
@InterceptorBinding
@Retention(RUNTIME)
@Target(TYPE)
public @interface SLAAudit
{

}
