import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.HTTP;
import org.json.JSONObject;

import com.google.gson.Gson;

import data.PeopleRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.People;
import models.PeopleResponse;

@WebServlet("/people")
public class PeopleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PeopleServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<People> peoples = new ArrayList<People>();

		HttpSession session = request.getSession();
		String idDocument = (String) session.getAttribute("idPeoplesList");

		if (idDocument != null) {
			peoples = new PeopleRepository().getAll(idDocument);
		}

		PeopleResponse peopleResponse = new PeopleResponse(peoples);

		Gson gson = new Gson();
		String jsonResult = gson.toJson(peopleResponse, peopleResponse.getClass());

		response.getWriter().append(jsonResult);

		response.setStatus(200);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		// request.getParameter("birthDate");
		String cpf = request.getParameter("cpf");
		String cep = request.getParameter("cep");
		int addressNumber = Integer.parseInt(request.getParameter("addressNumber"));
		String complement = request.getParameter("complement");

		People people = new People();
		people.setId(id);
		people.setName(name);
		// people.setBirthDate(birthDate);
		people.setCpf(cpf);
		people.setCep(cep);
		people.setAddressNumber(addressNumber);
		people.setComplement(complement);

		HttpSession session = request.getSession();
		List<People> peoples = (List<People>) session.getAttribute("peoplesList");

		if (peoples == null) {
			peoples = new ArrayList<>();
			peoples.add(people);
		} else {
			peoples.add(people);
		}

		PeopleResponse peopleResponse = new PeopleResponse(peoples);

		HttpSession sessionPeoples = request.getSession();
		sessionPeoples.setAttribute("peoplesList", peoples);

		PeopleResponse peopleResp = new PeopleResponse(peoples);

		Gson gson = new Gson();
		String jsonResult = gson.toJson(peopleResp, peopleResp.getClass());

		response.getWriter().append(jsonResult);

		response.setStatus(200);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
		} finally {
			reader.close();
		}

		JSONObject jsonObject = new JSONObject(sb.toString());				
		
		int id = jsonObject.getInt("id");
		String name = jsonObject.getString("name");
		String cpf = jsonObject.getString("cpf");
		String cep = jsonObject.getString("cep");
		int addressNumber = jsonObject.getInt("addressNumber");
		String complement = jsonObject.getString("complement");

		People people = new People();
		people.setId(id);
		people.setName(name);
		people.setCpf(cpf);
		people.setCep(cep);
		people.setAddressNumber(addressNumber);
		people.setComplement(complement);		
		
		HttpSession session = request.getSession();
		String idDocument = (String) session.getAttribute("idPeoplesList");

		if (idDocument == null) {
			PeopleResponse peopleResponse = new PeopleResponse(false, "Não existe sessão setada");

			Gson gson = new Gson();
			String jsonResult = gson.toJson(peopleResponse, peopleResponse.getClass());

			response.getWriter().append(jsonResult);

			response.setStatus(200);

			return;
		}

		List<People> peoples = new ArrayList<People>(new PeopleRepository().getAll(idDocument));

		peoples.set(id, people);

		new PeopleRepository().edit(peoples, idDocument);

		PeopleResponse peopleResp = new PeopleResponse(peoples);

		Gson gson = new Gson();
		String jsonResult = gson.toJson(peopleResp, peopleResp.getClass());

		response.getWriter().append(jsonResult);

		response.setStatus(200);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<People> peoples;

		String idDocument = (String) session.getAttribute("idPeoplesList");

		if (idDocument != null) {
			peoples = new ArrayList<People>(new PeopleRepository().getAll(idDocument));
		} else {
			PeopleResponse peopleResponse = new PeopleResponse(false, "Sessão expirada");

			Gson gson = new Gson();
			String jsonResult = gson.toJson(peopleResponse, peopleResponse.getClass());

			response.getWriter().append(jsonResult);

			response.setStatus(200);

			return;
		}

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
		} finally {
			reader.close();
		}

		JSONObject jsonObject = new JSONObject(sb.toString());
		int id = jsonObject.getInt("id");

		int index = -1;
		for (int i = 0; i < peoples.size(); i++) {
			if (peoples.get(i).getId() == id) {
				index = i;
				break;
			}
		}

		// not found
		if (index == -1) {
			PeopleResponse peopleResponse = new PeopleResponse(false, "Id não encontrado");

			Gson gson = new Gson();
			String jsonResult = gson.toJson(peopleResponse, peopleResponse.getClass());

			response.getWriter().append(jsonResult);
			response.setStatus(404);
			return;
		}

		peoples.remove(index);

		new PeopleRepository().edit(peoples, idDocument);

		PeopleResponse peopleResponse = new PeopleResponse();
		Gson gson = new Gson();
		String jsonResult = gson.toJson(peopleResponse, peopleResponse.getClass());

		response.getWriter().append(jsonResult);
		response.setStatus(200);
	}
}
