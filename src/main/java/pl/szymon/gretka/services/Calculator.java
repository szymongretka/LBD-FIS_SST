package pl.szymon.gretka.services;

import java.util.List;
import java.util.Map;

import pl.szymon.gretka.entity.SurveyAnswers;

public interface Calculator {
	
	public Map<String, Double> calculate(List<SurveyAnswers> list);
	
}
