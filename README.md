# Springboot Starter - Calculator
Simple Calculator App to practice using Java Springboot

This calculator can:
- add a set of numbers
- determine whether a number is prime

## Getting Started
This project requires Java Springboot and Maven to run.

Relevant Links:
https://spring.io/guides/gs/spring-boot/

https://maven.apache.org/download.cgi

First install dependencies
`mvn install`

Then package the src code
`mvn package`

Finally run the .jar file
`java -jar target/calculator-0.0.1-SNAPSHOT.jar`

## API Reference Guide

/hello - GET Request
- Sample endpoint that returns "Hello World!"

/add - POST Request
- Input: List of numbers
- Output: Sum of input list

/prime/{value} - GET Request
- Returns whether {value} is prime or not


