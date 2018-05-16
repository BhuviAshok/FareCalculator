package com.klm.travelapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.klm.travelapi.model.Metrics;
import com.klm.travelapi.service.MetricsService;

@CrossOrigin
@RestController
@RequestMapping("/custom-metrics")
public class MetricsController {
	
	 @Autowired
	    private MetricsService metricsService;

	    @RequestMapping(method = RequestMethod.GET)
	    public @ResponseBody Metrics getMetrics() {

	        return metricsService.getMetrics();
	    }

}
