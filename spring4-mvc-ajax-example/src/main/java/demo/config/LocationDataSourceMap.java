package demo.config;

import java.util.HashMap;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class LocationDataSourceMap extends HashMap<Object, Object> implements ApplicationContextAware
{

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		this.applicationContext = applicationContext;
	}

	@Override
	public Object get(Object key)
	{
		Object value = super.get(key);
		if (value == null)
		{

			// Can't autowire this because it apparently creates a chicken/egg
			// problem during configuration.
			//TenantRepository repo = applicationContext.getBean(TenantRepository.class);

			//Tenant tenant = repo.findOne((String) key);

			java.util.Properties properties = (java.util.Properties) applicationContext.getBean("mapper");

			//Tenant tenant = repo.findOne((String) key);

			//if (tenant != null)
			//{
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl(properties.getProperty(key + ".url"));
			dataSource.setUsername(properties.getProperty(key + ".username"));
			dataSource.setPassword(properties.getProperty(key + ".password"));

			value = dataSource;
			super.put(key, value);
			//}
		}
		return value;
	}

}
