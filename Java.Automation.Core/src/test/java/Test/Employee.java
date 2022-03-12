package Test;

import RestAssured.API.Helper.JsonParser;
import contract.Users;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Employee {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://reqres.in/";
		RequestSpecification request = RestAssured.given();

		Users user = new Users();
		user.setName("Henry Mars");
		user.setJob("Software Engineer");
		JsonParser jsonParser = new JsonParser();

		String jsonRequest = jsonParser.SerializeToJson(user);

		request.body(jsonRequest);

		Response response = request.post("api/users");

		int statusCode = response.getStatusCode();
		String body = response.body().asPrettyString();

		System.out.println(body);
		Users responseObject = jsonParser.DeserializeToObject(body, Users.class);
	}

}
