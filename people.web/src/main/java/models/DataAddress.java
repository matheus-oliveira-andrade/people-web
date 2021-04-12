package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class DataAddress {
	public Address getAddress(String cep) throws IOException {

		URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		con.connect();

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line).append('\n');
		}
		
		if(sb.toString() == null || sb.toString() == "")
			return null;
		
		JSONObject jObj = new JSONObject(sb.toString());
		Address address = new Address();
		address.setCep(jObj.getString("cep"));
		address.setStreet(jObj.getString("logradouro"));
		address.setcomplement(jObj.getString("complemento"));
		address.setDistrict(jObj.getString("bairro"));
		address.setLocality(jObj.getString("localidade"));
		address.setUf(jObj.getString("uf"));		
		

		return address;		
	}
}
