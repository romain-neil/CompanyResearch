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

public class Index extends HttpServlet {

	@Serial
	private static final long serialVersionUID = -5602071631160504168L;
	
	private final Map<String, Business> liste = new HashMap<>();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession s = request.getSession();
		
		if(s.getAttribute("liste") == null) {
			s.setAttribute("liste", liste);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

		s.setAttribute("message", null);
	}

}
