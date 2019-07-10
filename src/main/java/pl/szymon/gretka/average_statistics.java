package pl.szymon.gretka;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/average_statistics")
public class average_statistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	Map<String, Double> AverageMap = new HashMap<>();
	
	public Map<String, Double> CalculateAverage(Map<String, ArrayList<String>> map) {
		
		for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
			
			String key = entry.getKey();
			List<String> stringValues = entry.getValue();
			
			Double sum = stringValues.stream()
					.mapToDouble(num->Double.parseDouble(num)).sum();
			Double average = sum / stringValues.size();
			
			AverageMap.put(key, average);
		}		
		
		return AverageMap;
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		ServletContext context = request.getServletContext();
		List<SurveyAnswers> listOfSurveys = (ArrayList) context.getAttribute("answers");
		
		Map<String, ArrayList<String>> Result = new HashMap<>();
		
		for(SurveyAnswers survey : listOfSurveys){
		    
		    	Result.putIfAbsent(survey.universityName, new ArrayList<String>());
		    	Result.get(survey.universityName).add(survey.quality);
		    	Result.get(survey.universityName).add(survey.contact);
		    	Result.get(survey.universityName).add(survey.experience);
		    	
		}
		
		
		/*Result = listOfSurveys.stream().collect(Collectors //chcialem dzialac tylko na 1 kolekcji ale mi nie wyszlo :(
				.toMap(SurveyAnswers::getUniversityName, avg -> {
					Double quality = Double.parseDouble(avg.getQuality());
					Double contact = Double.parseDouble(avg.getContact());
					Double experience = Double.parseDouble(avg.getExperience());
					Double average = (quality+contact+experience)/3;
					return average;
				}));*/

		CalculateAverage(Result);
		
		out.println("Average results for universities:");
		
		for (Map.Entry<String, Double> entry : AverageMap.entrySet()) {
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
		
		context.setAttribute("fileCSV", AverageMap);
	}

}
