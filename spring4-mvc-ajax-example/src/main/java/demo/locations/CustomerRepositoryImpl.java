package demo.locations;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom<Customer>
{

	@Override
	public String someCustomMethod(String user)
	{
		System.out.println("Custom Impl....@@@");
		return "Custom Code";
	}

}
