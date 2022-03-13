package RestAssured.API.Helper;

import io.restassured.response.Response;

public class RestResponse<T> implements IRestResponse<T> {

	private T data;
	private Response response;
	private Exception ex;

	public RestResponse(Class<T> type, Response response) {
		this.response = response;
		try {
			this.data = type.getDeclaredConstructor().newInstance();
		} catch (Exception ex) {
			throw new RuntimeException("There should be a default constructor in the Response POJO");
		}
	}

	public T getBody() {
		try {
			data = (T) response.getBody().as(data.getClass());
		} catch (Exception ex) {
			this.ex = ex;
		}
		return data;
	}

	public String getContent() {
		return response.body().asString();
	}

	public int getStatusCode() {
		return response.getStatusCode();
	}

	public boolean isSuccessful() {
		int code = response.getStatusCode();
		if (code == 200 || code == 201 || code == 202 || code == 203 || code == 204 || code == 205)
			return true;
		return false;
	}

	public String getStatusDescription() {
		return response.getStatusLine();
	}

	public Response getResponse() {
		return response;
	}

	public Exception getException() {
		return this.ex;
	}

}
