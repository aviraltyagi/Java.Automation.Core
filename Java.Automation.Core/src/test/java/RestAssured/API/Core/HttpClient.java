package RestAssured.API.Core;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.NotImplementedException;

import RestAssured.API.Helper.AuthorizationType;
import RestAssured.API.Helper.RequestFormat;
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

	public Response ExecutePostRequest(String relativeUrl, String request) {
		return _underlyingClient.body(request).post(relativeUrl);
	}

	public Response ExecuteGetRequest(String relativeUrl) {
		return _underlyingClient.get(relativeUrl);
	}

	public Response ExecutePutRequest(String relativeUrl, String request) {
		return _underlyingClient.body(request).put(relativeUrl);
	}

	public Response ExecuteDeleteRequest(String relativeUrl) {
		return _underlyingClient.delete(relativeUrl);
	}

	public Response ExecutePatchRequest(String relativeUrl, String request) {
		return _underlyingClient.body(request).patch(relativeUrl);
	}

}
