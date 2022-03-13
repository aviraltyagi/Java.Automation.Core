package RestAssured.API.Core;

import java.util.HashMap;

import RestAssured.API.Helper.IRestResponse;
import RestAssured.API.Helper.RestResponse;
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

	public <TRequest, TResponse> IRestResponse<TResponse> ExecutePostRequest(String relativeUrl, TRequest request,
			Class<TResponse> clazz) {
		IRestResponse<TResponse> response = HttpClient.ExecutePostRequest(relativeUrl, request, clazz);
		response.getBody();
		return response;
	}

	public <TResponse> IRestResponse<TResponse> ExecuteGetRequest(String relativeUrl, Class<TResponse> clazz) {
		IRestResponse<TResponse> response = HttpClient.ExecuteGetRequest(relativeUrl, clazz);
		response.getBody();
		return response;
	}

	public <TRequest, TResponse> IRestResponse<TResponse> ExecutePutRequest(String relativeUrl, TRequest request,
			Class<TResponse> clazz) {
		IRestResponse<TResponse> response = HttpClient.ExecutePutRequest(relativeUrl, request, clazz);
		response.getBody();
		return response;
	}

	public <TResponse> IRestResponse<TResponse> ExecuteDeleteRequest(String relativeUrl, Class<TResponse> clazz) {
		IRestResponse<TResponse> response = HttpClient.ExecuteDeleteRequest(relativeUrl, clazz);
		response.getBody();
		return response;
	}

	public <TRequest, TResponse> IRestResponse<TResponse> ExecutePatchRequest(String relativeUrl, TRequest request,
			Class<TResponse> clazz) {
		IRestResponse<TResponse> response = HttpClient.ExecutePatchRequest(relativeUrl, request, clazz);
		response.getBody();
		return response;
	}
}
