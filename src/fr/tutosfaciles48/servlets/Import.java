package fr.tutosfaciles48.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mkyong.csv.CSVReader;
import fr.tutosfaciles48.beans.Fichier;
import fr.tutosfaciles48.forms.ImportForm;
import fr.tutosfaciles48.util.Constants;

public class Import extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4616242187861932761L;
	
	public static final String PATH = "import_path";
	
	public static final String VUE = "/WEB-INF/import.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'envoi de fichiers */
		this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = this.getServletConfig().getInitParameter(PATH);
		
		System.out.println("Upload path: " + path);
		
		ImportForm form = new ImportForm();
		
		Fichier file = form.saveFile(request, path);
		String filePath = path + file.getName();
		
		System.out.println("Importing file " + filePath);
		System.out.println("Résultat de l'upload: " + form.getResult());
		
		if(!form.getErrors().isEmpty()) {
			System.err.println("Error during import !");
			form.getErrors().forEach((k, v) -> System.out.println(k + " : " + v));
		} else {
			try {
				CSVReader r = new CSVReader(filePath, request);
				
				String separator = Constants.CSV_DEFAULT_SEPARATOR;
				
				/*
				switch(request.getAttribute("separator").toString()) {
					case "2": // ;
						separator = ";";
						break;
				}*/
				
				r.read(separator);
				r.endProcess();
			} catch(Exception e) {
				System.err.println("une erreur s'est produite: " + e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		response.sendRedirect(this.getServletContext().getContextPath() + "/");
	}

}
