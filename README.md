## Project Title
Simple Rest Service Application to Retreive User Github Repositories by User Name

## Project Description
This project is a simple web application that provides user an REST endpoint which takes in a username and will then
return the data in JSON format as specified below.
```{
user_name: "octocat",
display_name: "The Octocat",
avatar: "https://avatars3.githubusercontent.com/u/583231?v=4",
geo_location: "San Francisco",
email: null,
url: "https://github.com/octocat ",
created_at: "2011-01-25 18:44:36",
repos: [{
name: "boysenberry-repo-1",
url: "https://github.com/octocat/boysenberry-repo-1"
}, ...
]
 }
  ```
## Design
Based on Controller-Service-Repository or Presentation-Domain-DataSource layering patterns. 

Controller - acts as the interface or presentation for the API with a simple username input.

Model - is our service/domain layer handling domain object, transformation of github user data, as well as interaction with the github API.

Data - Since we're not working with a traditional database or cache this was used to implement a mock caching service that could be "disabled" to simulate poor performance.

## Technologies Used
Java, Spring boot, Maven, Swagger

## Prerequisites:
This project assumes you have JDK 23 (Download here ```https://www.oracle.com/pk/java/technologies/downloads/#java23``` )
and Maven (Download here ```https://maven.apache.org/download.cgi``` ) installed and are familiar with their use.

## Installation
1. Clone the repository
   ```https://github.com/guaipeier421/simpleRestService.git```

2. Navigate to the project directory
  ```cd {your project path}\simpleRestService\simpleRestService\complete```

3. Install Dependencies:
   ```mvn clean install``` 

4. Start the application: Under path 
   ```{your project path}\simpleRestService\complete```
   use the following command
   ```java -jar target\rest-service-complete-0.0.1-SNAPSHOT.jar```
   
## Test for endpoint
1. Open your web browser.
2. Go to ```http://localhost:8080/swagger-ui/index.html#/Demo/getReposByUserName```
3. Click on "Try it out", then type "octocat" in the user name filed and click on "Execute"
4. You should be able to see the response data in the required JSON format.

5. You can also run ```curl -X 'GET' 'http://localhost:8080/repos/octocat' -H 'accept: application/json'```

## Test for caching:
1. Comment out @Cacheable annotation in OutputResponseRepositoryImpl.java
2. Comment out  @EnableCaching annotation in RestServiceApplication.java
3. Run ```mvn clean install``` 
4. Run ```java -jar target/rest-service-complete-0.0.1-SNAPSHOT.jar```
5. Look for logs in the Console
  ``` "com.example.restservice.AppRunner: Fetching User Repos->com.example.restservice.testModel.OutputResponse@2e590b" ```
   It will run 5 times and there will be a 3 sec delay in between each calls.
6. Remove comment for @Cacheable in OutputResponseRepositoryImpl.java and  @EnableCaching in RestServiceApplication.java
7. Run ```mvn clean install``` and ```java -jar target/rest-service-complete-0.0.1-SNAPSHOT.jar``` again 
   and you will see that the first retrieval still takes three seconds.  However, the second and subsequent 
   times for the same user are much faster, showing that the cache is doing its job.

## Next Steps (TODO):
1. Add unit tests
2. Add java doc for each public function and class
  

