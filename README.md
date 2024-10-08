# clarify4j

## Introduction

**clarify4j**: A Java 1.8 library enhancing Spring Boot applications with a comprehensive set of annotations and
pointcuts. It simplifies aspect-oriented programming by providing an opinionated framework for cleaner and more
maintainable code, enabling developers to leverage the full power of Spring with minimal boilerplate.

## Features

- Comprehensive set of utility functions.
- Written in Java 1.8.
- Well-documented code for easy understanding.
- Regular updates and maintenance.

## Installation

```bash
git clone --depth 1 https://github.com/sivaosorg/clarify4j.git
```

## Generation Plugin Java

```bash
curl https://gradle-initializr.cleverapps.io/starter.zip -d type=groovy-gradle-plugin  -d testFramework=testng -d projectName=clarify4j -o clarify4j.zip
```

## Modules

Explain how users can interact with the various modules.

### Tidying up

To tidy up the project's Java modules, use the following command:

```bash
./gradlew clean
```

or

```bash
make clean
```

### Building SDK

```bash
./gradlew jar
```

or

```bash
make jar
```

### Upgrading version

- file `gradle.yml`

```yaml
ng:
  name: clarify4j
  version: v1.0.0
  enabled_link: false # enable compression and attachment of the external libraries
  jars:
    # unify4J: Java 1.8 skeleton library offering a rich toolkit of utility functions
    # for collections, strings, date/time, JSON, maps, and more.
    - enabled: false # enable compression and attachment of the external libraries
      source: "./../libs/unify4j-v1.0.0.jar"
    # alpha4J: is a Java 8 library featuring common data structures and algorithms.
    # Enhance your projects with efficient and easy-to-use implementations designed for performance and clarity.
    - enabled: true
      source: "./../libs/alpha4j-v1.0.0.jar"
```

## Add dependencies

```groovy
// The "spring-core" library, version 5.3.31, is a fundamental component of the Spring Framework,
// offering essential functionality for dependency injection, bean management, and core utilities to facilitate robust Java application development within the Spring ecosystem.
implementation group: 'org.springframework', name: 'spring-core', version: '5.3.31'
// The "spring-boot-starter-web" library, version 2.7.18, is a Spring Boot starter module that facilitates the setup of web applications,
// providing essential dependencies and configurations for building web-based projects.
implementation group: 'org.springframework.boot', name: 'spring-boot-starter-web', version: '2.7.18'
// The "spring-boot-configuration-processor" library, version 2.7.18,
// is a Spring Boot module that processes configuration metadata annotations to generate metadata files and aid in auto-configuration of Spring applications.
implementation group: 'org.springframework.boot', name: 'spring-boot-configuration-processor', version: '2.7.18'
// The "spring-boot-starter-test" module version 2.7.18 provides a comprehensive test framework for Spring Boot applications.
// It includes JUnit, Mockito, Spring TestContext Framework, and other useful tools for testing Spring applications.
// The starter integrates these components seamlessly, making it easier to write and execute tests in a Spring Boot environment.
testImplementation group: 'org.springframework.boot', name: 'spring-boot-starter-test', version: '2.7.18'
// Mockito JUnit Jupiter version 3.12.4: This library integrates Mockito with JUnit 5,
// enabling developers to write unit tests using Mockito's powerful mocking features.
// It's designed to work specifically with the JUnit 5 platform, allowing for advanced testing capabilities.
testImplementation group: 'org.mockito', name: 'mockito-junit-jupiter', version: '3.12.4'
// The "spring-boot-starter-security" library, version 2.7.18, is an essential component of Spring Boot applications,
// offering robust security features to safeguard your application's endpoints, authenticate users, and manage access control effectively.
implementation group: 'org.springframework.boot', name: 'spring-boot-starter-security', version: '2.7.18'
// The "spring-boot-starter-aop" library, version 2.7.18, provides support for Aspect-Oriented Programming (AOP) in Spring Boot applications.
// AOP enables modularization of cross-cutting concerns such as logging, security, and transactions by allowing aspects to be applied to various parts of the application.
// This starter simplifies the setup and configuration of AOP-related functionality, promoting cleaner and more maintainable code by separating concerns effectively.
implementation group: 'org.springframework.boot', name: 'spring-boot-starter-aop', version: '2.7.18'
```

## Integration

1. Add dependency into file `build.gradle`

```gradle
implementation files('libs/clarify4j-v1.0.0.jar') // filename based on ng.name and ng.version
```

2. Edit file `main Spring Boot application` (optional)

```java

@SpringBootApplication
@ComponentScan(basePackages = {"your_package", "org.clarify4j"}) // root name of package clarify4j
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
```
