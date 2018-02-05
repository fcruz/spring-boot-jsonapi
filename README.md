#Spring-boot-jsonapi

Receives a String and returns JSON formatted Geocode information from maps.googleapis.

##Usage
JDK8 or later and Maven 3.0+ are needed

Clone and run with **mvn spring-boot:run** 

If you prefer create a jar **mvn package** and then **java -jar target/fcruz-0.0.1-SNAPSHOT.jar**

Access following URL on your favorite browser
http://localhost:8080/location?address=Dam+Square+Amsterdam

Or use curl

POST
curl -d address=Dam+Square+Amsterdam http://localhost:8080/location

GET
curl http://localhost:8080/location?address=Dam+Square+Amsterdam


##Running Tests
**mvn test** or **mvn -Dtest=com.example.fcruz.LocationControllerTest test**
(class can be omitted if you want)
