package pl.szymon.gretka.servlets;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.szymon.gretka.entity.SurveyAnswers;


@WebServlet("/surveys")
public class SurveysServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String UNIVERSITY_NAME = "universityName";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String STUDY_DEGREE = "studyDegree";
	private static final String QUALITY = "quality";
	private static final String CONTACT = "contact";
	private static final String EXPERIENCE = "experience";
	
	private Integer counter = 0;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = config.getServletContext();
		context.setAttribute("answers", new ArrayList<SurveyAnswers>());
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/lbd/surveys.html");
	}
       
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		counter++;
		PrintWriter out = response.getWriter();
		
		//HttpSession session = request.getSession(true);

		ServletContext context = request.getServletContext();
		List<SurveyAnswers> answers = (ArrayList) context.getAttribute("answers");
		
		out.print("Thanks ");
		out.print(request.getParameter(FIRST_NAME) + " ");
		out.print(request.getParameter(LAST_NAME));
		out.println(" for participation in our university survey.");
		out.println("Your answers: ");
		out.println("University name: " + request.getParameter(UNIVERSITY_NAME));
		out.println("Your study degree: " + request.getParameter(STUDY_DEGREE));
		out.println("List of answers with scoring for each question");
		out.println("Quality of courses and teaching?" + request.getParameter(QUALITY));
		out.println("Contact with teachers?" + request.getParameter(CONTACT));
		out.println("Inclusion of work/practical experience?" + request.getParameter(EXPERIENCE));
		out.println("Number of successful survey submission’s responses: " + counter);
		
		answers.add(new SurveyAnswers(request.getParameter(FIRST_NAME), request.getParameter(LAST_NAME), request.getParameter(UNIVERSITY_NAME),
				request.getParameter(STUDY_DEGREE), request.getParameter(QUALITY), request.getParameter(CONTACT), 
				request.getParameter(EXPERIENCE)));
		
		
	}

}
