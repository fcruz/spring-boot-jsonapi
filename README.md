# Spring-boot-jsonapi
  
  Receives a String and returns JSON formatted Geocode information from maps.googleapis.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

JDK8 or later and Maven 3.0+

### Installing

Clone the project
```
git clone https://github.com/fcruz/spring-boot-jsonapi.git

```
 
Run with maven
```
cd spring-boot-jsonapi
mvn spring-boot:run 
```

If you prefer create a jar and execute with java command
```
cd spring-boot-jsonapi
mvn package
java -jar target/fcruz-0.0.1-SNAPSHOT.jar

```


### Usage

Access following URL on your favorite browser

```
http://localhost:8080/location?address=Dam Square Amsterdam
```

Or use curl
```
POST: curl -d address=Dam+Square+Amsterdam http://localhost:8080/location
GET: curl http://localhost:8080/location?address=Dam+Square+Amsterdam
```

Expected Response

```
{"formatted_address":"Dam Square, Dam, 1012 JL Amsterdam, Netherlands","latitude":"52.3730677","longitude":"4.8926390"}
```

## Running the tests

class can be omitted if you want
```
mvn test or 

```
or
```
mvn -Dtest=com.example.fcruz.LocationControllerTest test

```

## Swagger enabled API

http://localhost:8080/swagger-ui.html

## Built With

* [SpringBoot](https://projects.spring.io/spring-boot/) - The web framework used
* [Apache Camel](https://github.com/apache/camel/blob/master/README.md) - Used to build the integration
* [Maven](https://maven.apache.org/) - Dependency Management


## Author

* **Fabio Cruz** - [fcruz](https://github.com/fcruz)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
