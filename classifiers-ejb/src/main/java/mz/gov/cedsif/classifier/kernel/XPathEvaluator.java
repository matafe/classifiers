
package mz.gov.cedsif.classifier.kernel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.jxpath.JXPathContext;

/**
 * XPath Evaluator
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
public class XPathEvaluator
{
	private static final Logger LOG = Logger.getLogger(XPathEvaluator.class
		.getName());

	private static XPathEvaluator instance = new XPathEvaluator();

	private XPathEvaluator()
	{
	}

	public static XPathEvaluator getInstance()
	{
		return instance;
	}

	/**
	 * Evaluate the XPath expression.
	 * 
	 * @param instances The collection of instances
	 * @param type The type
	 * @param expression The XPath expression
	 * 
	 * @return The collection
	 */
	public <E> Collection<E> evaluate(Collection<E> instances,
			String type,
			String expression)
	{
		Map<String, Collection<E>> map = new HashMap<>(1, 1.0f);
		map.put(type, instances);

		JXPathContext context = JXPathContext.newContext(map);

		LOG.info("XPath Ëxpression: " + expression);

		@SuppressWarnings("unchecked")
		Iterator<E> iterator = context.iterate(type + expression);

		List<E> result = new ArrayList<E>();

		while (iterator.hasNext())
		{
			result.add(iterator.next());
		}

		return result;
	}
}
