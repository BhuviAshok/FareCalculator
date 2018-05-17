package com.klm.travelapi.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.klm.travelapi.model.Fare;

import com.klm.travelapi.model.FareResponse;
import com.klm.travelapi.model.Location;
import com.klm.travelapi.service.AirportService;
import com.klm.travelapi.service.FareService;

@CrossOrigin
@RestController
@RequestMapping("retriveAirFare/{origin}/{destination}")
public class AirFareController {
	
	@Autowired
	private FareService fareService;
	
	@Autowired
	private AirportService airportService;         
	
	
	@Value("${klm.api.timeout}")
	private long timeout;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Fare findFare(@PathVariable("origin") String origin,
			@PathVariable("destination") String destination)
			throws IOException, ExecutionException, InterruptedException, TimeoutException {

		if (origin.isEmpty() || destination.isEmpty()) {
			return null;
		}

		// get origin airport
		Future<Location> locationOriginFuture = airportService.findAirportByCode(origin);
		Location locationOrigin = locationOriginFuture.get(timeout, TimeUnit.SECONDS);

		// get destination airport
		Future<Location> locationDestinationFuture = airportService.findAirportByCode(destination);
		Location locationDestination = locationDestinationFuture.get(timeout, TimeUnit.SECONDS);

		// check if origin and destination are valid
		if (locationOrigin == null || locationDestination == null) {
			return null;
		}

		// get fare response for origin - destination
		Future<FareResponse> fareResponseFuture = fareService.findFare(origin, destination);
		FareResponse fareResponse = fareResponseFuture.get(timeout, TimeUnit.SECONDS);

		// create new fare
		Fare fare = new Fare(fareResponse);
		fare.setOrigin(locationOrigin);
		fare.setDestination(locationDestination);
		return fare;
		
	}
	
	

}
