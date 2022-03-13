package RestAssured.API.Core;

import java.util.HashMap;

import io.restassured.http.Cookie;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Context {
	public HttpClient HttpClient;
	private String _baseUrl;

	public void setHttpClient(Cookie cookie, HashMap<String, String> customHeaders) {
		HttpClient = new HttpClient(_baseUrl, cookie, customHeaders);
	}

	public void setBaseUrl(String baseUrl) {
		this._baseUrl = baseUrl;
	}

	public String LastResult;
	public int StatusCode;
	public String ContentType;
	public Headers ResponseHeaders;

	public void setLastResult(Response response) {
		StatusCode = response.statusCode();
		ContentType = response.contentType();
		ResponseHeaders = response.headers();
		LastResult = response.asPrettyString();
	}

	public String ExecutePostRequest(String relativeUrl, String requestBody) {
		Response response = HttpClient.ExecutePostRequest(relativeUrl, requestBody);
		setLastResult(response);
		return response.asPrettyString();
	}

	public String ExecuteGetRequest(String relativeUrl) {
		Response response = HttpClient.ExecuteGetRequest(relativeUrl);
		setLastResult(response);
		return response.asPrettyString();
	}

	public String ExecutePutRequest(String relativeUrl, String requestBody) {
		Response response = HttpClient.ExecutePutRequest(relativeUrl, requestBody);
		setLastResult(response);
		return response.asPrettyString();
	}

	public String ExecuteDeleteRequest(String relativeUrl) {
		Response response = HttpClient.ExecuteDeleteRequest(relativeUrl);
		setLastResult(response);
		return response.asPrettyString();
	}

	public String ExecutePatchRequest(String relativeUrl, String requestBody) {
		Response response = HttpClient.ExecutePatchRequest(relativeUrl, requestBody);
		setLastResult(response);
		return response.asPrettyString();
	}
}
