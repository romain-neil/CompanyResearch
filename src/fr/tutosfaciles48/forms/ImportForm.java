package fr.tutosfaciles48.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
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
	private Map<String, String> errors = new HashMap<String, String>();
	
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
			setError(FIELD_FILE, "Les donn�es envoy�es sont trop volumineuses.");
		} catch(IOException e) {
			e.printStackTrace();
			setError(FIELD_FILE, "Erreur de configuration du serveur");
		} catch(ServletException e) {
			e.printStackTrace();
			setError(FIELD_FILE, "Ce type de requ�te n'est pas support�, merci d'utiliser le formulaire pr�vu pour envoyer le fichier.");
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
				setError(FIELD_FILE, "Erreur lors de l'�criture du fichier sur le disque.");
			}
		}
		
		if(errors.isEmpty()) {
			result = "Succ�s de l'envoi du fichier";
			errors.clear();
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
			throw new Exception("Merci de s�lectionner un fichier � envoyer");
		}
	}
	
	private void setError(String field, String message) {
		errors.put(field,  message);
	}
	
	private void writeFile(InputStream content, String path, String fileName) throws Exception {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		
		try {
			in = new BufferedInputStream(content, BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(new File(path + fileName)));
			
			byte[] buffer = new byte[BUFFER_SIZE];
			int length = 0;
			
			while((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
		} finally {
			try {
				out.close();
			} catch(IOException ignore) {}
			try {
				in.close();
			} catch(IOException ignore) {}
		}
	}
	
}
