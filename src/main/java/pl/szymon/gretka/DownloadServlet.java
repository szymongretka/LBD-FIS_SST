package pl.szymon.gretka;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext context = request.getServletContext();
		Map<String, Double> results = (HashMap) context.getAttribute("fileCSV");

		StringBuilder sb = new StringBuilder();
		
		Map<String, Double> sortedMap = results
		        .entrySet()
		        .stream()
		        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
		        .collect(Collectors
		            .toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
		                LinkedHashMap::new));
		
		
		
		for (Entry<String, Double> entry : sortedMap.entrySet()) {
		    sb.append("University name: ").append(entry.getKey())
		    .append(" average: ").append(entry.getValue())
		    .append("\r\n");
		}
		
		response.setContentType("text/csv");
	    response.setHeader("Content-Disposition", "attachment; filename=\"statistics.csv\"");
	    try {
	        OutputStream outputStream = response.getOutputStream();
	        outputStream.write(sb.toString().getBytes());
	        outputStream.flush();
	        outputStream.close();
	    } catch(Exception e) {
	        System.out.println(e.toString());
	    }
	}


}
