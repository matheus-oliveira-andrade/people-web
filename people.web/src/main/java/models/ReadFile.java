package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadFile {
	
	public String Read(InputStream fileContent) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(fileContent, "UTF-8"));

		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line + System.lineSeparator());
		}
		
		return sb.toString();
	}
	
}
