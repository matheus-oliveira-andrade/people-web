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
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.ConvertToPeople;
import models.ReadFile;

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

		System.out.println(filePath);

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

		Part filePart = request.getPart("arquivo");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		InputStream fileContent = filePart.getInputStream();

		System.out.println(fileName);

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(fileContent, "UTF-8"));

		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line + System.lineSeparator());
		}

		List<String> lines = Arrays.asList(sb.toString().split("\\r?\\n"));

		System.out.println(lines);

		// Type listType = new TypeToken<List<String>>() {}.getType();

		// Gson gson = new Gson();
		// String jsonResult = gson.toJson(lines, listType);

		response.setStatus(200);
	}

}
