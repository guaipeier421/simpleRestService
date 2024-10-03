# Project Title
Simple Rest Service Application to Retreive User Github Repositories by User Name

## Project Description
This project is a simple web application that provides user an REST endpoint which takes in a username and will then
return the data in JSON format as specified below.
{
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

## Technologies Used
Java, Spring boot, Maven

## Installation
1. Clone the repository
   git clone  

2. Navigate to the project directory
   cd your-repo

3. Install Dependencies:
   mvn clean install 

4. Start the applilcation by typing following command under path {your project path}\gs-rest-service-main-1001\complete
   java -jar target\rest-service-complete-0.0.1-SNAPSHOT.jar
   
## Test for endpoint
1. Open your web browser.
2. Go to http://localhost:8080/swagger-ui/index.html#/Demo/getReposByUserName
3. Type in "octocat" in the user name and click on "Execute"
4. You should be able to see the response data in required JSON format.

## Test for caching:
1. Comment out @Cacheable annotation in OutputResponseRepositoryImpl.java
2. Comment out  @EnableCaching annotation in RestServiceApplication.java
3. Run mvn clean install 
4. Run java -jar target/rest-service-complete-0.0.1-SNAPSHOT.jar
5. Look for logs in the Console "com.example.restservice.AppRunner: Fetching User Repos-->com.example.restservice.testModel.OutputResponse@2e590b". 
   It will run 5 times and there will be a 3 sec delay in between each calls.
5. Remove comment for @Cacheable in OutputResponseRepositoryImpl.java and  @EnableCaching in RestServiceApplication.java
6. Run mvn clean install and java -jar target/rest-service-complete-0.0.1-SNAPSHOT.jar again 
   and you will see that the first retrieval of a book still takes three seconds.  However, the second and subsequent 
   times for the same book are much faster, showing that the cache is doing its job.


