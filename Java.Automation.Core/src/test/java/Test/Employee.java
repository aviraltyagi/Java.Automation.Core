package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import RestAssured.API.Application.Helper.Endpoints;
import RestAssured.API.Core.Context;
import RestAssured.API.Core.HttpClient;
import RestAssured.API.Helper.IRestResponse;
import RestAssured.Application.API.Methods.UserHelper;
import contract.Users;

public class Employee {
	public Context _context = new Context();

	public Employee() {
		_context.setBaseUrl(Endpoints.Base_Url);
	}

	@Test
	public void FirstTest() {
		_context.setHttpClient(null, null);
		UserHelper userHelper = new UserHelper(_context);
		IRestResponse<Users> response = userHelper.createUser("Tom", "Developer");
		Assert.assertEquals(response.getStatusCode(), 201);
	}
}
