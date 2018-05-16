package com.klm.travelapi.serviceimpl;


import java.io.IOException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klm.travelapi.model.Location;
import com.klm.travelapi.model.LocationSearchResponse;
import com.klm.travelapi.model.LocationSearchResult;
import com.klm.travelapi.service.AirportService;

/**
 * Created by Bhuvaneswari.
 */
@Service
public class AirportServiceImpl implements AirportService {

	@Value("${klm.api.url}")
	private String baseUrl;

	@Value("${klm.api.airportUrl}")
	private String airportUrl;

	@Value("${klm.api.airportsUrl}")
	private String airportsUrl;

	@Autowired
	private OAuth2RestTemplate restTemplate;

	@Autowired
	private ObjectMapper mapper;

	@Async
	public Future<Location> findAirportByCode(String code) {
		String url = String.format(airportUrl, baseUrl, code);
		Location location = restTemplate.getForObject(url, Location.class);
		return new AsyncResult<Location>(location);
	}

	@Async
	@Override
	public Future<LocationSearchResult> findAirport(String searchString) throws IOException {
		String urlTemplate = searchString.isEmpty() ? airportUrl : airportsUrl;
		String url = String.format(urlTemplate, baseUrl, searchString);
		String json = restTemplate.getForObject(url, String.class);

		LocationSearchResponse response = mapper.readValue(json.toString(), LocationSearchResponse.class);

		return new AsyncResult<LocationSearchResult>(response.get_embedded());
	}
}
