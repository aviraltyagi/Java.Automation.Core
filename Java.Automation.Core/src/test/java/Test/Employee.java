package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import RestAssured.API.Core.Context;
import RestAssured.API.Core.HttpClient;
import RestAssured.Application.API.Methods.UserHelper;

public class Employee {
	public Context _context = new Context();

	public Employee() {
		_context.setBaseUrl("https://reqres.in/");
	}

	@Test
	public void FirstTest() {
		_context.setHttpClient(null, null);
		UserHelper userHelper = new UserHelper(_context);
		userHelper.createUser("Tom", "Developer");
		Assert.assertEquals(_context.StatusCode, 201);
	}
}
