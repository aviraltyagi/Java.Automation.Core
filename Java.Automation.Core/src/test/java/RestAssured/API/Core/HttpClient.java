package RestAssured.API.Core;

import java.util.HashMap;

import RestAssured.API.Helper.RequestFormat;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;

public class HttpClient {
	
	

	public HttpClient(String baseURI, RequestFormat requestFormat, Cookie cookie,
			HashMap<String, String> customHeaders) {
		if (baseURI == null) {
			throw new IllegalArgumentException("BaseURL is null");
		}
		RestAssured.baseURI = baseURI;
	}
	
	

}
