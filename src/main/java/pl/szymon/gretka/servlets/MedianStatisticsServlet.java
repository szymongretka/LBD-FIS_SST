package pl.szymon.gretka.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.szymon.gretka.entity.SurveyAnswers;
import pl.szymon.gretka.services.interfaces.Calculator;
import pl.szymon.gretka.services.qualifiers.Median;


@WebServlet("/median-statistics")
public class MedianStatisticsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Map<String, Double> MedianMap = new HashMap<>();
	
	@Median
	@Inject
	Calculator calculator;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		ServletContext context = request.getServletContext();
		List<SurveyAnswers> listOfSurveys = (ArrayList) context.getAttribute("answers");
		
		MedianMap = calculator.calculate(listOfSurveys);
		
		out.println("Median results for universities:");
		
		for (Map.Entry<String, Double> entry : MedianMap.entrySet()) {
			out.println("University name: " + entry.getKey() + " median: " + entry.getValue());
		}
		
		
	}


}
