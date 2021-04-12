import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;

import data.PeopleRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.People;

@WebServlet("/export")
@MultipartConfig
public class ExportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExportServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idDocument = request.getParameter("idDocument");
		System.out.println(idDocument);

		if (idDocument == null) {
			response.setStatus(500);
			return;
		}

		List<People> peoples = new PeopleRepository().getAll(idDocument);

		if (peoples.size() > 0) {
			String caminhoArquivoGerado;

			String filePath = "/resources/modelo_" + UUID.randomUUID().toString() + ".csv";
			String relativePath = "C:\\Users\\Matheus\\OneDrive - Complexo de Ensino Superior do Brasil LTDA\\7 Semestre\\Tópicos Esp. em Desenv. de Sistemas\\CrudPeople\\people.web";

			BufferedWriter writter = Files.newBufferedWriter(Paths.get(relativePath + filePath), StandardCharsets.UTF_8,
					StandardOpenOption.CREATE);

			for (People people : peoples) {
				System.out.println(people.toString());
				writter.write(people.toString());
				writter.newLine();
			}
			
			writter.close();

			caminhoArquivoGerado = relativePath + filePath;

			File downloadFile = new File(caminhoArquivoGerado);
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

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
