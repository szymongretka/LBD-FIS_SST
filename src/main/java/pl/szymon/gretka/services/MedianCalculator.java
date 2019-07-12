package pl.szymon.gretka.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pl.szymon.gretka.entity.SurveyAnswers;

@Median
public class MedianCalculator implements Calculator {

	Map<String, Double> map = new HashMap<>();
	
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
			
			Collections.sort(nums);
			
			double median;
			if (nums.size() % 2 == 0)
			    median = ((double)nums.get(nums.size()/2) + (double)nums.get(nums.size()/2 - 1))/2;
			else
			    median = (double) nums.get(nums.size()/2);

			
			map.put(key, median);
			});
		
		return map;
	}

}
