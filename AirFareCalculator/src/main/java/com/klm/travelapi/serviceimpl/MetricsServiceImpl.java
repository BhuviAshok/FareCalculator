package com.klm.travelapi.serviceimpl;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.stereotype.Service;

import com.klm.travelapi.model.Metrics;
import com.klm.travelapi.service.MetricsService;
import com.klm.travelapi.utils.MetricsUtil;

@Service
public class MetricsServiceImpl implements MetricsService {
	
	

	@Autowired
	private MetricsEndpoint metricsEndpoint;

	

	@Override
	public Metrics getMetrics() {

		Metrics metrics = new Metrics();

		Set<Map.Entry<String, Object>> metricSet = metricsEndpoint.invoke().entrySet();
		
		metrics.setNumberOfRequests(MetricsUtil.sum(metricSet, "counter.status."));
		metrics.setNumberOfRequestsOK(MetricsUtil.sum(metricSet, "counter.status.2"));
		metrics.setNumberOfRequests4xx(MetricsUtil.sum(metricSet, "counter.status.4"));
		metrics.setNumberOfRequests5xx(MetricsUtil.sum(metricSet, "counter.status.5"));
		metrics.setMinResponseTime(MetricsUtil.get(metricSet, "gauge.response.min"));
		metrics.setMaxResponseTime(MetricsUtil.get(metricSet, "gauge.response.max"));
		metrics.setAverageResponseTime(MetricsUtil.get(metricSet, "gauge.response.total"));
		if (metrics.getNumberOfRequests() > 0) {
			metrics.setAverageResponseTime(metrics.getAverageResponseTime() / metrics.getNumberOfRequests());
		}

		return metrics;
	}
	
	
}
