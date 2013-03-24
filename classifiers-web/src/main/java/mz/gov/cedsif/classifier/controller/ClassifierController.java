
package mz.gov.cedsif.classifier.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import mz.gov.cedsif.classifier.boundary.IClassifierFacade;
import mz.gov.cedsif.classifier.boundary.IClassifierQueryFacade;
import mz.gov.cedsif.classifier.entity.Classifier;
import mz.gov.cedsif.classifier.entity.Management;
import mz.gov.cedsif.classifier.entity.ResourceSource;
import mz.gov.cedsif.classifier.entity.Scope;
import mz.gov.cedsif.classifier.entity.Territory;
import mz.gov.cedsif.classifier.util.ClassifierFactory;

/**
 * Classifier Controller
 * 
 * @author <a href="mailto:matafe@gmail.com">Mauricio T. Ferraz</a>
 */
@Model
public class ClassifierController
{

	@Inject
	private FacesContext facesContext;

	@Inject
	private IClassifierFacade classifierFacade;

	@Inject
	private IClassifierQueryFacade classifierQueryFacade;

	private ClassifierFormBean classifierFormBean;

	private List<Classifier> classifiers = new ArrayList<Classifier>();

	private String codeToSearch;

	@Produces
	@Named
	public List<Classifier> getClassifiers()
	{
		return classifiers;
	}

	public String getCodeToSearch()
	{
		return codeToSearch;
	}

	@Produces
	@Named
	public ClassifierFormBean getClassifierFormBean()
	{
		return classifierFormBean;
	}

	public Map<String, String> getClassifierTypes()
	{
		// List<String> classifierTypes = this.classifierQuertFacade
		// .findAllClassifierTypes();
		Map<String, String> types = new LinkedHashMap<>();
		types.put("Ambito", Scope.TYPE);
		types.put("Fonte de Rescurso", ResourceSource.TYPE);
		types.put("Gestao", Management.TYPE);
		types.put("Territorio", Territory.TYPE);

		return types;

	}

	@SuppressWarnings("unchecked")
	public void searchClassifier() throws Exception
	{
		Class<? extends Classifier> classType = ClassifierFactory
			.newInstanceForType(this.classifierFormBean.getType()).getClass();

		if (codeToSearch == null || codeToSearch.isEmpty())
		{
			this.classifiers = (List<Classifier>) this.classifierQueryFacade
				.findAllClassifiersByType(classType);
		}
		else
		{
			Classifier found = this.classifierQueryFacade
				.findClassifierByTypeAndCode(classType, codeToSearch);
			System.out.println("found: " + found);
			this.classifiers.clear();
			this.classifiers.add(found);
		}
	}

	/**
	 * @param codeToSearch the codeToSearch to set
	 */
	public void setCodeToSearch(String codeToSearch)
	{
		this.codeToSearch = codeToSearch;
	}

	public void createClassifier() throws Exception
	{
		Classifier classifier = ClassifierFactory
			.newInstanceForType(classifierFormBean.getType());
		classifier.setCode(classifierFormBean.getCode());
		classifier.setDescription(classifierFormBean.getDescription());
		classifier.setRemarks(classifierFormBean.getRemarks());

		try
		{
			this.classifierFacade.create(classifier);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Criado!",
					"Criado com sucesso");
			this.facesContext.addMessage(null, m);
			initNewClassifier();
		}
		catch (Exception e)
		{
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					errorMessage,
					"Falha na criacao");
			this.facesContext.addMessage(null, m);
		}
	}

	@SuppressWarnings("unchecked")
	public void onClassifierListChanged(@Observes final Classifier classifier)
		throws Exception
	{
		Class<? extends Classifier> classType = ClassifierFactory
			.newInstanceForType(this.classifierFormBean.getType()).getClass();
		this.classifiers = (List<Classifier>) this.classifierQueryFacade
			.findAllClassifiersByType(classType);
	}

	@PostConstruct
	public void initNewClassifier() throws Exception
	{
		this.classifierFormBean = new ClassifierFormBean();
	}

	private String getRootErrorMessage(Exception e)
	{
		// Default to general error message that registration failed.
		String errorMessage = "Creation failed. See server log for more information";
		if (e == null)
		{
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null)
		{
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}
}
