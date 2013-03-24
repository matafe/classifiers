
package mz.gov.cedsif.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Service Level Agreement Monitor Interceptor.
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
@SLAAudit
@Interceptor
public class SLAMonitorInterceptor
{
	private static final Logger LOG = Logger
		.getLogger(SLAMonitorInterceptor.class.getName());

	@AroundInvoke
	public Object measurePerformance(InvocationContext context)
		throws Exception
	{
		long start = System.currentTimeMillis();
		try
		{
			return context.proceed();
		}
		finally
		{
			long end = System.currentTimeMillis();
			LOG.log(Level.INFO, "Method: {0} performed in {1}", new Object[]{
					context.getMethod(),
					end - start});
		}
	}
}