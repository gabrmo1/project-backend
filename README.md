# project-backend
This project is a representation of a solid foundation for an application, with implementations necessary for proper functioning, in addition to essential and optional features.

## Resources

### Spring Security and JWT authentication
It is advised that the SECRET_KEY constant be changed in [JwtService.java](src/main/java/br/com/project/config/JwtService.java).
A key with the same format can be generated at https://allkeysgenerator.com/Random/Security-Encryption-Key-Generator.aspx.

Settings:
- 256 bit
- Hex: Yes

If you want to use another authentication format, please change the settings to your preferred format.\
All configuration classes will be in src/main/java/us/com/project/config.

### Endpoints prepared to receive and return content in json, xml and yml.
To implement, just receive the constants in the consumes and produces attributes.

Example:\
```consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}```\
```produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}```

see examples in [ExampleController.java](src/main/java/br/com/project/controllers/ExampleController.java)
### HATEOAS
The Entity/Dto must extend *RepresentationModel\<Class>* as in *ExampleDto.java*.\
After that, you can use the .add method to insert links from your specific endpoints as in *ExampleUtils.java*.

Developed using [Spring HATEOAS - Reference Documentation](https://docs.spring.io/spring-hateoas/docs/current/reference/html/). 

see examples in [ExampleDto.java](src/main/java/br/com/project/dtos/ExampleDto.java), [ExampleService.java](src/main/java/br/com/project/services/ExampleService.java) and [ExampleUtils.java](src/main/java/br/com/project/util/example/ExampleUtils.java)


