package RestAssured.API.Core;

import java.util.HashMap;
import java.util.Map;
import RestAssured.API.Helper.AuthorizationType;
import RestAssured.API.Helper.IRestResponse;
import RestAssured.API.Helper.JsonParser;
import RestAssured.API.Helper.RestResponse;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HttpClient {

	public HttpClient(String baseURI, Cookie cookie, HashMap<String, String> customHeaders) {
		try {
			if (baseURI == null)
				throw new IllegalArgumentException("BaseURL is null");
			_underlyingClient = RestAssured.given();
			_underlyingClient.baseUri(baseURI);

			CustomHeader = (customHeaders != null) ? customHeaders : new HashMap<String, String>();

			if (cookie != null) {
				_cookies.cookies(cookie, null);
			} else {
				_cookies = new Cookies();
			}
			_underlyingClient.cookies(_cookies);
			if (Authorization != null)
				_underlyingClient.header(Authorization);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private RequestSpecification _underlyingClient;

	private Cookies _cookies;

	private Map<String, String> CustomHeader;

	private Header Authorization;

	public Map<String, String> getCustomHeader() {
		return this.CustomHeader;
	}

	public void setAuthorization(AuthorizationType authType, String token) throws Exception {
		String authTypeText;
		switch (authType) {
		case Basic:
			authTypeText = "Basic";
			break;
		case Bearer:
			authTypeText = "Bearer";
			break;
		case AuthId:
			authTypeText = "AuthId";
			break;
		default:
			throw new Exception("The operation for the enumeration value " + authType + " is not implemented.");
		}
		Authorization = new Header("Authorization", "authTypeText " + token);
		_underlyingClient.header(Authorization);
	}

	public <TRequest, TResponse> IRestResponse<TResponse> ExecutePostRequest(String relativeUrl, TRequest request,
			Class<TResponse> clazz) {
		JsonParser jsonParser = new JsonParser();
		String requestBody = jsonParser.SerializeToJson(request);
		Response response = _underlyingClient.body(requestBody).post(relativeUrl);
		return new RestResponse<TResponse>(clazz, response);
	}

	public <TResponse> IRestResponse<TResponse> ExecuteGetRequest(String relativeUrl, Class<TResponse> clazz) {
		Response response = _underlyingClient.get(relativeUrl);
		return new RestResponse<TResponse>(clazz, response);
	}

	public <TRequest, TResponse> IRestResponse<TResponse> ExecutePutRequest(String relativeUrl, TRequest request,
			Class<TResponse> clazz) {
		JsonParser jsonParser = new JsonParser();
		String requestBody = jsonParser.SerializeToJson(request);
		Response response = _underlyingClient.body(request).put(relativeUrl);
		return new RestResponse<TResponse>(clazz, response);
	}

	public <TResponse> IRestResponse<TResponse> ExecuteDeleteRequest(String relativeUrl, Class<TResponse> clazz) {
		Response response = _underlyingClient.delete(relativeUrl);
		return new RestResponse(clazz, response);
	}

	public <TRequest, TResponse> IRestResponse<TResponse> ExecutePatchRequest(String relativeUrl, TRequest request,
			Class<TResponse> clazz) {
		JsonParser jsonParser = new JsonParser();
		String requestBody = jsonParser.SerializeToJson(request);
		Response response = _underlyingClient.body(request).patch(relativeUrl);
		return new RestResponse<TResponse>(clazz, response);
	}

}
