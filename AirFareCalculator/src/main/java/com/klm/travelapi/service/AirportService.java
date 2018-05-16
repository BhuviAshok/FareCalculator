package com.klm.travelapi.service;

import java.io.IOException;
import java.util.concurrent.Future;

import com.klm.travelapi.model.Location;
import com.klm.travelapi.model.LocationSearchResult;

public interface AirportService {

	
	public Future<Location> findAirportByCode(String code);

	public Future<LocationSearchResult> findAirport(String searchString) throws IOException;
	
}
