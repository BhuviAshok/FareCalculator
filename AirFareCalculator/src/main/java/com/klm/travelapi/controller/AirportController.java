package com.klm.travelapi.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.klm.travelapi.model.Location;
import com.klm.travelapi.model.LocationSearchResult;
import com.klm.travelapi.service.AirportService;

@RestController
@RequestMapping("/airports")
public class AirportController {
	
	@Autowired
	AirportService airportService;
	

    @Value("${klm.api.timeout}")
    private long timeout;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Location> findAirport(
            @RequestParam(value = "term", required = false, defaultValue = "") String searchString)
            throws ExecutionException, InterruptedException, IOException, TimeoutException {

        Future<LocationSearchResult> locationResponseFuture = airportService.findAirport(searchString);
        LocationSearchResult locationSearchResult = locationResponseFuture.get(timeout, TimeUnit.SECONDS);

        if (locationSearchResult == null) {
            return null;
        }

        List<Location> locations = locationSearchResult.getLocations();

        return locations;
    }
	
	

}
