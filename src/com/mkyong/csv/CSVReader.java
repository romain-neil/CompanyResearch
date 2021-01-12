package com.mkyong.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import fr.tutosfaciles48.beans.Business;
import fr.tutosfaciles48.util.Constants;

public class CSVReader {
	
	private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
	private final HttpSession session;
	private final String file;
	private final DateTimeFormatter formatter;
	
	private final Map<String, Business> entreprises;
	
	@SuppressWarnings("unchecked")
	public CSVReader(String f, HttpServletRequest request) {
		session = request.getSession();
		file = f;
		entreprises = (HashMap<String, Business>) session.getAttribute("liste");
		formatter = DateTimeFormat.forPattern(DATE_PATTERN);
	}

    public void read(String separator) throws Exception {
        String line;
        String[] result;
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
            	if(i == 0) {
            		i++;
            		continue;
            	}
                result = line.split(separator);
                processLine(result, session);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void processLine(String[] line, HttpSession session) throws Exception {
    	Business b = new Business();

    	int fileVersion = Integer.parseInt(line[0]);
    	
    	if(fileVersion < Constants.INTERNAL_CSV_VERSION) {
    		throw new Exception("The file format is too old to be processed for the moment. Conversions will come in the future.");
    	} else if(fileVersion > Constants.INTERNAL_CSV_VERSION) {
    		throw new Exception("The file format is too newer to be processed for the moment. COnversions will come in the future.");
    	}

    	//(file_)version,Name,Address,DateAdded,ReCall,isHiring,uuid
    	b.setName(line[1]);
    	b.setAddress(line[2]);
    	b.setDateAdded(formatter.parseDateTime(line[3]));
    	
    	boolean recall = line[4].equals("Oui");
    	b.setRecall(recall);
    	
    	b.setIsHiring(Boolean.parseBoolean(line[5]));
    	b.setEmail(line[6]);
    	b.setPhone(line[7]);
    	
    	String uuid = line[8];
    	if(uuid == null) {
    		uuid = UUID.randomUUID().toString();
    	}
    	
    	b.setUuid(uuid);
    	
    	entreprises.put(uuid,  b);
    }
    
    public void endProcess() {
    	session.setAttribute("liste",  entreprises);
    }

}