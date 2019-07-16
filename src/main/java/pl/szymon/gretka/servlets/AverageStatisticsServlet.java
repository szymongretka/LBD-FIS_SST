package pl.szymon.gretka.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.szymon.gretka.entity.SurveyAnswers;
import pl.szymon.gretka.services.interfaces.Calculator;
import pl.szymon.gretka.services.qualifiers.Average;
import pl.szymon.gretka.services.qualifiers.Median;

@WebServlet("/average_statistics")
public class AverageStatisticsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private Map<String, Double> averageMap = new HashMap<>();
	
	@Average
	@Inject
	Calculator calculator;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		ServletContext context = request.getServletContext();
		List<SurveyAnswers> listOfSurveys = (ArrayList) context.getAttribute("answers");
		
		if(listOfSurveys != null) {
			averageMap = calculator.calculate(listOfSurveys);
			out.println("Average results for universities:");
		} else {
			out.println("There is no data to display<br>");
		}

		
		
		for (Map.Entry<String, Double> entry : averageMap.entrySet()) {
			out.println("<br>" + "University name: " + entry.getKey() + " average: " + entry.getValue());
		}
		
		response.setContentType("text/html charset=utf-8");
		
		out.println("<html><head><meta charset=\'UTF-8\'>");
		out.println("<title>Statistics</title></head><body>");
		out.println("<form action=/lbd/download>");
		out.println("<button>Download</button>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html><br>");
		
		context.setAttribute("fileCSV", averageMap);
	}

}
