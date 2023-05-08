# REST ASSURED - API automation framework

1) Java library (API) for testing RESTful web services.
2) Used to test XML and JSON based web services.
3) Supports GET, POST, PATCH, PUT, DELETE, HEAD, OPTIONS. Can be integrated with testing frameworks like JUnit, TestNG.
4) Rest Assured is implement in Groovy. It uses Groovy under the hood to fetch the JSON object (GPath Expression)

REST:

- Representation State Transfer is an architectural style sending the representational state of an object at that
  particular time. - Rest API is Stateless (i.e., Server will not store any information about the client)
  JSON:
- Key value pair where key is always a String and Value can be String, number, boolean, JSON Object, JSON Array, null
  etc., - {} --> JSON Object - [] --> JSON Array

Path Param - Used to identify specific resource, it is mandatory. Query Param - Used to Sort/filter the resources, it is
optional.

### Quick Start

	- Java
	- IntelliJ IDE
	- Maven
	- TestNG


### Validate JSON Schema

1) Create or Get JSON Schema
2) Add JSON Schema file in src/test/resources path
3) Add maven dependency for JSON Schema validator

### Validate XML Schema

1) Create or Get XML Schema (XSD)
2) Add XSD in src/test/resources path

### Different ways of constructing POST request

1. As String
2. From external file (.json file)
3. Read it as String from external file (.json file) and replace values.
4. Using HashMap (for Json Object {} ) and ArrayList (for Json Array []).
5. Using external Json library (JSONObject, JSONArray).

### Build Docker Image

```
docker build -t <imagename>:<tag> .
```

### Create docker image on top of a container (Base image)

```
docker commit <<containerid>>
docker tag <<container/image id>> <<newImageName>>
docker build -t <<newImageName>>
```

### Push image to docker hub

```
docker login
docker push <<imagename>>
```
