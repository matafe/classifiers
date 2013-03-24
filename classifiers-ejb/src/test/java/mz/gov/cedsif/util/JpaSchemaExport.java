
package mz.gov.cedsif.util;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.ejb.Ejb3Configuration;

@SuppressWarnings("deprecation")
public class JpaSchemaExport
{

	public static void main(String[] args) throws IOException
	{
		args = new String[4];
		args[0] = "primary";
		args[1] = "./target/schema.sql";
		args[2] = "true";
		args[3] = "true";
		
		execute(args[0],
				args[1],
				Boolean.parseBoolean(args[2]),
				Boolean.parseBoolean(args[3]));
	}

	public static void execute(String persistenceUnitName,
			String destination,
			boolean create,
			boolean format)
	{
		System.out.println("Starting schema export");
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", H2Dialect.class.getName());
		Ejb3Configuration cfg = new Ejb3Configuration()
			.configure(persistenceUnitName, properties);
		Configuration hbmcfg = cfg.getHibernateConfiguration();
		org.hibernate.tool.hbm2ddl.SchemaExport schemaExport = new org.hibernate.tool.hbm2ddl.SchemaExport(hbmcfg);
		schemaExport.setOutputFile(destination);
		schemaExport.setFormat(format);
		schemaExport.execute(true, false, false, create);
		System.out.println("Schema exported to " + destination);
	}
}