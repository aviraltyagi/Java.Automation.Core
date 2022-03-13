package RestAssured.Application.API.Methods;

import RestAssured.API.Core.Context;
import RestAssured.API.Helper.JsonParser;
import contract.Users;

public class UserHelper {
	private Context _context;

	public UserHelper(Context context) {
		_context = context;
	}

	public void createUser(String name, String job) {
		Users user = new Users();
		user.setName(name);
		user.setJob(job);
		JsonParser jsonParser = new JsonParser();
		String requestBody = jsonParser.SerializeToJson(user);

		_context.ExecutePostRequest("api/users", requestBody);
	}

}
