
package mz.gov.cedsif.classifier.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Classifier Territoty - Entity
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
@XmlRootElement
@Entity
@Table(name = "RESOURCE_SOURCE", uniqueConstraints = @UniqueConstraint(columnNames = "CODE"))
public class ResourceSource
	extends Classifier
{
	private static final long serialVersionUID = 1L;

	/** The length of the classifier code. */
	public static final int LENGTH = 12;

	/** The internal classifier class identifier. */
	public static final String TYPE = "resource-source";

	/**
	 * The resource source code.
	 */
	@NotEmpty
	@Size(min = ResourceSource.LENGTH, max = ResourceSource.LENGTH)
	@Column(nullable = false, length = ResourceSource.LENGTH)
	private String code;

	/**
	 * Default Constructor
	 */
	public ResourceSource()
	{
	}

	/**
	 * Constructor
	 * 
	 * @param code The code
	 */
	public ResourceSource(final String code)
	{
		super(code);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType()
	{
		return TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCode()
	{
		return code;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCode(String code)
	{
		this.code = code;
	}

}
