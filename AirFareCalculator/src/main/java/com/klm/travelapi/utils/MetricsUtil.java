package com.klm.travelapi.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class MetricsUtil {

	public static long get(Set<Map.Entry<String, Object>> metricSet, String prefix) {
		List<Map.Entry<String, Object>> list = metricSet.stream().filter(byPrefix(prefix)).collect(Collectors.toList());
		if (!list.isEmpty()) {
			return toLong(list.get(0));
		}
		return 0;
	}

	public  static int sum(Set<Map.Entry<String, Object>> metricSet, String prefix) {
		return metricSet.stream().filter(byPrefix(prefix)).mapToInt(value -> toInt(value)).sum();
	}

	public  static Predicate<Map.Entry<String, Object>> byPrefix(String prefix) {
		return e -> e.getKey().startsWith(prefix);
	}

	public  static int toInt(Map.Entry<String, Object> entry) {
		return Integer.parseInt(entry.getValue().toString());
	}

	public static long toLong(Map.Entry<String, Object> entry) {
		return (long) Double.parseDouble(entry.getValue().toString());
	}

	
}
