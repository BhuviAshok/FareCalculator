package com.klm.travelapi.serviceimpl;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;


import com.klm.travelapi.model.FareResponse;
import com.klm.travelapi.service.FareService;

@Service
public class FareServiceImpl implements FareService {
	
	 @Value("${klm.api.url}")
	    private String baseUrl;

	    @Value("${klm.api.faresUrl}")
	    private String faresUrl;

	    @Value("${klm.api.timeout}")
	    private long timeout;

	    @Autowired
	    private OAuth2RestTemplate restTemplate;

	@Override
	@Async
	public Future<FareResponse> findFare(String origin, String destination) {
		 String url = String.format(faresUrl, baseUrl, origin, destination);
		 System.out.println("URL--------->"+url);
		 FareResponse fareResponse = restTemplate.getForObject(url, FareResponse.class);
	        return new AsyncResult<FareResponse>(fareResponse);
		
	}

}
