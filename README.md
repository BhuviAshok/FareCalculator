# AirFare Calculator

Technologies used:-

	1. SpringBoot
	2. Angular 1.5
	3. Bootstrap CSS
	3. FreeMarker Template
	4. Rest API
  	5. Spring Oauth 2.0
	6. RestAssured Test Runner
	7. Maven

Steps to run the application:-

	1. Clone the repo  'AirFareCalculator' and 'simple-travel-api-mock' into your favourite IDE (Eclipse preferred)
	2. Update project with maven/gradle to download all application level depencies.
	3. Start the 'simple-travel-api-mock' application first to boots up the API at port 8080.
	4. Now start the 'AirFareCalculator' and hit the URL  http://localhost:9090/travel to view the application.
	
Steps to run the test scripts:-

	1. Just run 'mvn test' on top of 'AirFareCalculator' project.
	2. Test execution results will be displayed in the console.
	
	
Note: You can change the default port by updating the port number present in application.yml and configure in app.js

Project description:

	1. The homepage will have 2 tabs - Home and Metrics.
	2. By default Home tab will be displayed with 2 textboxes (Origin and Destination) with autocomplete feature.
	3. After selecting the Origin and Destination aiports, the user can view the Fare Details on clicking  'Retrieve Fare' button.
	4. An available flight will be displayed in tabular fashion.
	5. User can view the transaction related custom metrics on clicking the  'Metrics' tab.
	