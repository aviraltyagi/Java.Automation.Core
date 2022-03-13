package RestAssured.Application.API.Methods;

import org.testng.Assert;

import RestAssured.API.Application.Helper.Endpoints;
import RestAssured.API.Core.Context;
import RestAssured.API.Helper.IRestResponse;
import RestAssured.API.Helper.JsonParser;
import contract.Users;

public class UserHelper {
	private Context _context;

	public UserHelper(Context context) {
		_context = context;
	}

	public IRestResponse<Users> createUser(String name, String job) {
		Users user = new Users();
		user.setName(name);
		user.setJob(job);
		IRestResponse<Users> response = _context.ExecutePostRequest(Endpoints.Users, user, Users.class);
		if(!response.isSuccessful())
			Assert.fail("Unable to create the user");
		return response;
	}

}
