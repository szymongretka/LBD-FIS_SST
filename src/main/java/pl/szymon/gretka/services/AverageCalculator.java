package pl.szymon.gretka.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.szymon.gretka.entity.SurveyAnswers;
import pl.szymon.gretka.services.interfaces.Calculator;
import pl.szymon.gretka.services.qualifiers.Average;

@Average
public class AverageCalculator implements Calculator {

	private Map<String, Double> map = new HashMap<>();
	
	@Override
	public Map<String, Double> calculate(List<SurveyAnswers> list) {
		
		Map<String, ArrayList<Double>> tempMap = new HashMap<>();	
		
		list.forEach(temp -> {

			String key = temp.getUniversityName();
			
			tempMap.putIfAbsent(key, new ArrayList<>());
			tempMap.get(key).add(Double.parseDouble(temp.quality));
			tempMap.get(key).add(Double.parseDouble(temp.contact));
			tempMap.get(key).add(Double.parseDouble(temp.experience));
			
			List<Double> nums = tempMap.get(key);
			
			Double sum = nums.stream().mapToDouble(Double::doubleValue).sum();
			
			Double average = sum / nums.size();
			
			map.put(key, average);
		});
		
		return map;
	}

}
