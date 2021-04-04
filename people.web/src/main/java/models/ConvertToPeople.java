package models;

import java.util.Arrays;
import java.util.List;

public class ConvertToPeople {

	public void toPeople(String fileText) {

		List<String> lines = Arrays.asList(fileText.split("\\r?\\n"));
		
		System.out.println(lines);
		
		// Type listType = new TypeToken<List<String>>() {}.getType();		 
		
		// Gson gson = new Gson();
		// String jsonResult = gson.toJson(lines, listType);
	}
}