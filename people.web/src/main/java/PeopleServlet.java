import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import models.ConvertToPeople;
import models.People;
import models.PeopleResponse;

@WebServlet("/people")
@MultipartConfig
public class PeopleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PeopleServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String filePath = "/resources/modelo.txt";
		String relativePath = "C:\\Users\\Matheus\\OneDrive - Complexo de Ensino Superior do Brasil LTDA\\7 Semestre\\Tópicos Esp. em Desenv. de Sistemas\\CrudPeople\\people.web";
		// getServletContext().getRealPath("");

		File downloadFile = new File(relativePath + filePath);
		String mimeType = Files.probeContentType(downloadFile.toPath());

		FileInputStream inStream = new FileInputStream(downloadFile);

		// Set header response to download
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		// output stream response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[4096];
		int bytesRead = -1;

		while ((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inStream.close();
		outStream.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			Part filePart = request.getPart("arquivo");
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			InputStream fileContent = filePart.getInputStream();

			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(fileContent, "UTF-8"));

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + System.lineSeparator());
			}

			List<String> lines = Arrays.asList(sb.toString().split("\\r?\\n"));

			List<People> peoples = new ArrayList<>();

			SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");

			for (int i = 0; i < lines.size(); i++) {
				String[] rowSplited = lines.get(i).split(";");

				People people = new People();
				people.setId(Integer.parseInt(rowSplited[0]));
				people.setName(rowSplited[1]);
				// people.setBirthDate(formatterDate.parse(rowSplited[2]));
				people.setCpf(rowSplited[3]);
				people.setCep(rowSplited[4]);
				people.setAddressNumber(Integer.parseInt(rowSplited[5]));
				people.setComplement(rowSplited[6]);

				peoples.add(people);
			}

			PeopleResponse peopleResponse = new PeopleResponse(peoples);

			HttpSession session = request.getSession();
			session.setAttribute("peoplesList", peoples);
			
			Gson gson = new Gson();
			String jsonResult = gson.toJson(peopleResponse, peopleResponse.getClass());

			response.getWriter().append(jsonResult);

			response.setStatus(200);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
