
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
@Table(name = "TERRITORY", uniqueConstraints = @UniqueConstraint(columnNames = "CODE"))
public class Territory
	extends Classifier
{
	private static final long serialVersionUID = 1L;

	/** The length of the classifier code. */
	public static final int LENGTH = 10;

	/** The internal classifier class identifier. */
	public static final String TYPE = "territory";

	/**
	 * The territory code.
	 */
	@NotEmpty
	@Size(min = Territory.LENGTH, max = Territory.LENGTH)
	@Column(nullable = false, length = Territory.LENGTH)
	private String code;

	/**
	 * Default Constructor
	 */
	public Territory()
	{
	}

	/**
	 * Constructor
	 * 
	 * @param code The code
	 */
	public Territory(final String code)
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
