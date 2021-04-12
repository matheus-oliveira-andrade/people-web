import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import data.PeopleRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Address;
import models.DataAddress;
import models.People;

@WebServlet(name = "pessoa-detalhes", urlPatterns = { "/pessoa-detalhes" })
public class DetalhesPessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DetalhesPessoaServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idDocument = (String) session.getAttribute("idPeoplesList");

		String id = request.getParameter("id");

		if (idDocument == null || id == null) {
			response.setStatus(404);
			return;
		}

		People people = new PeopleRepository().getById(idDocument, Integer.parseInt(id));

		if (people == null) {
			response.setStatus(404);
			return;
		}

		Address address = new DataAddress().getAddress(people.getCep());

		people.setAddress(address);

		request.setAttribute("people", people);

		RequestDispatcher rd = request.getRequestDispatcher("Details.jsp");
		rd.forward(request, response);

		// response.sendRedirect("Details.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
