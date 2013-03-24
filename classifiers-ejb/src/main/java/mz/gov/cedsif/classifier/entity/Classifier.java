
package mz.gov.cedsif.classifier.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import mz.gov.cedsif.classifier.util.IClassifier;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Abstract Classifier - Entity
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
@NamedQueries({
		@NamedQuery(name = Classifier.FIND_ALL_TYPES, query = "SELECT DISTINCT c.class FROM Classifier c", hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")}),
		@NamedQuery(name = Classifier.FIND_ALL, query = "FROM Classifier c", hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})})
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Cacheable
@Entity
@XmlRootElement
@XmlType(propOrder = {"id", "code", "description", "remarks", "type"})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Classifier
	implements IClassifier, Serializable
{
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL_TYPES = "Classifier.findAllTypes";

	public static final String FIND_ALL = "Classifier.findAll";

	public static final String FIND_BY_TYPE_AND_CODE = "Classifier.findByTypeAndCode";

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 255)
	private String description;

	@Column(length = 255)
	private String remarks;

	/**
	 * Default Constructor
	 */
	public Classifier()
	{
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param code The code
	 */
	public Classifier(final String code)
	{
		super();
		setCode(code);
	}

	/**
	 * @return the classifier type
	 */
	@XmlElement
	public abstract String getType();

	/**
	 * @return the classifier code
	 */
	public abstract String getCode();

	/**
	 * Set the code.
	 * 
	 * @param code the code to set.
	 */
	public abstract void setCode(final String code);

	/**
	 * @return the description.
	 */
	@Override
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return the id.
	 */
	@Override
	public Long getId()
	{
		return id;
	}

	/**
	 * @return the remarks.
	 */
	public String getRemarks()
	{
		return remarks;
	}

	/**
	 * Set the description.
	 * 
	 * @param description the description to set.
	 */
	public void setDescription(final String description)
	{
		this.description = description;
	}

	/**
	 * Set the id.
	 * 
	 * @param id the id to set.
	 */
	public void setId(final Long id)
	{
		this.id = id;
	}

	/**
	 * Set the remarks.
	 * 
	 * @param remarks the remarks to set.
	 */
	public void setRemarks(final String remarks)
	{
		this.remarks = remarks;
	}

	/**
	 * Hash Code method.
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((getCode() == null) ? 0 : getCode().hashCode());
		result = prime
				* result
				+ ((getType() == null) ? 0 : getType().hashCode());
		return result;
	}

	/**
	 * Equals method.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Classifier other = (Classifier) obj;
		if (getCode() == null)
		{
			if (other.getCode() != null)
				return false;
		}
		else if (!getCode().equals(other.getCode()))
			return false;
		if (getType() == null)
		{
			if (other.getType() != null)
				return false;
		}
		else if (!getType().equals(other.getType()))
			return false;
		return true;
	}

	/**
	 * To String method.
	 */
	@Override
	public String toString()
	{
		return getClass().getSimpleName()
				+ " [id="
				+ id
				+ ", code="
				+ getCode()
				+ ", type="
				+ getType()
				+ "]";
	}

}