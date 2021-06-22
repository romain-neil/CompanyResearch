package fr.tutosfaciles48.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import fr.tutosfaciles48.beans.Fichier;

public class ImportForm {
	
	private static final String FIELD_FILE = "fichier";
	private static final int BUFFER_SIZE = 10240;
	
	private String result;
	private final Map<String, String> errors = new HashMap<>();
	
	public String getResult() {
		return result;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	public Fichier saveFile(HttpServletRequest request, String path) {
		Fichier f = new Fichier();
		
		String fileName = null;
		InputStream fileContent = null;
		
		try {
			Part part = request.getPart(FIELD_FILE);
			
			fileName = getFileName(part);
			f.setName(fileName);
			
			fileContent = part.getInputStream();
		} catch(IllegalStateException e) {
			e.printStackTrace();
			setError(FIELD_FILE, "Les données envoyées sont trop volumineuses.");
		} catch(IOException e) {
			e.printStackTrace();
			setError(FIELD_FILE, "Erreur de configuration du serveur");
		} catch(ServletException e) {
			e.printStackTrace();
			setError(FIELD_FILE, "Ce type de requête n'est pas supporté, merci d'utiliser le formulaire prévu pour envoyer le fichier.");
		} catch(Exception e) {
			e.printStackTrace();
			setError(FIELD_FILE, "Erreur inconnue");
		}
		
		if(errors.isEmpty()) {
			try {
				validateFile(fileName, fileContent);
			} catch(Exception e) {
				setError("Validation", "Unable to validate file content");
				setError(FIELD_FILE, e.getMessage());
			}
		}
		
		if(errors.isEmpty()) {
			try {
				writeFile(fileContent, path, fileName);
			} catch(Exception e) {
				setError(FIELD_FILE, "Erreur lors de l'écriture du fichier sur le disque.");
			}
		}
		
		if(errors.isEmpty()) {
			result = "Succès de l'envoi du fichier";
		} else {
			result = "Echec de l'envoi du fichier.";
		}
		
		return f;
	}

	private String getFileName(final Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	private void validateFile(String fileName, InputStream fileContent) throws Exception {
		if(fileName == null || fileContent == null) {
			throw new Exception("Merci de sélectionner un fichier à envoyer");
		}
	}
	
	private void setError(String field, String message) {
		errors.put(field,  message);
	}
	
	private void writeFile(InputStream content, String path, String fileName) throws Exception {

		try (BufferedInputStream in = new BufferedInputStream(content, BUFFER_SIZE); BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path + fileName))) {
			byte[] buffer = new byte[BUFFER_SIZE];
			int length;

			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
		}
	}
	
}
