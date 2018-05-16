package com.klm.travelapi.service;

import java.util.concurrent.Future;


import com.klm.travelapi.model.FareResponse;

public interface FareService {

	public Future<FareResponse> findFare(String origin, String destination);
	
}
