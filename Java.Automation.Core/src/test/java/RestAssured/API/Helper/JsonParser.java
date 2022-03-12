package RestAssured.API.Helper;

import com.google.gson.Gson;

public class JsonParser {

	public <T> String SerializeToJson(T object) {
		String json = null;
		try {
			json = new Gson().toJson(object);
		} catch (Exception e) {
			System.out.println("Unable to convert " + object.getClass().getName() + " Error: " + e);
		}
		return json;
	}

	public <T> T DeserializeToObject(String jsonString, Class<T> clazz) {
		T object = null;
		try {
			object = new Gson().fromJson(jsonString, clazz);
		} catch (Exception e) {
			System.out.println("Unable to convert to json " + object.getClass().getName() + " Error: " + e);
		}
		return object;
	}

}
