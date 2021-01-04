package fr.tutosfaciles48.servlets;

import java.io.IOException;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.tutosfaciles48.beans.Business;
import fr.tutosfaciles48.forms.CreateBusinessForm;

public class CreateBusiness extends HttpServlet {

	public static final String VUE = "/WEB-INF/create.jsp";

	@Serial
	private static final long serialVersionUID = 7336220822974248171L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CreateBusinessForm form = new CreateBusinessForm();

		Business entreprise = form.createBusiness(request);

		request.setAttribute("entreprise",  entreprise);
		request.setAttribute("form", form);

		HttpSession session = request.getSession();
		
		if(entreprise != null) { //Si il n'y a pas d'erreurs
			@SuppressWarnings({ "unchecked" })
			Map<String, Business> entreprises = (HashMap<String, Business>) session.getAttribute("liste");
			
			if(entreprises == null) {
				entreprises = new HashMap<>();
			}

			entreprises.put(entreprise.getUuid(), entreprise);
			
			session.setAttribute("liste", entreprises);
			
			response.sendRedirect(this.getServletContext().getContextPath() + "/");
		} else {
			session.setAttribute("message", form.getError());

			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}
	
}
