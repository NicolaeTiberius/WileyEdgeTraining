package com.SpringMVC.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


//@RestController annotation to turn any Java class (a POJO) into a web-enabled controller.
// A @RestController is registered with the Spring DI container just like any other @Component,
// but it also enables the annotated class to handle RESTful HTTP requests with its methods.

@RestController

//@RequestMapping("/api") is our first mapping annotation. Mapping (or Routing),
// determines if a given URL, HTTP method, HTTP header, or media type triggers a specific controller method.
// By applying this annotation at the class level, we tell Spring MVC that this class can only handle URLs
// that begin with "/api".
// We don't have to map requests at the class level, but it's convenient. We can also map requests method by method.
@RequestMapping("/api")
public class SimpleController {

    //The method helloWorld returns a String[] to the Spring MVC framework,
    // which then serializes the result to JSON and includes it in the HTTP response body.
//    The @GetMapping signals to Spring MVC that this method can only handle HTTP requests using the GET method.
//    It can, but doesn't, further refine the accepted URL.
    @GetMapping
    public String[] helloWorld() {
        String[] result = {"Hello", "World", "!"};
        return result;


    }

    //@PostMapping("/calculate") tells Spring MVC to execute our method
    // if an HTTP request's method is POST and the URL is "/api/calculate".
    @PostMapping("/calculate")
    public String calculate(int operand1, String operator, int operand2) {
        int result = 0;
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
            default:
                String message = String.format("operator '%s' is invalid", operator);
                throw new IllegalArgumentException(message);
        }
        return String.format("%s %s %s = %s", operand1, operator, operand2, result);
    }


    //@DeleteMapping("/resource/{id}") tells Spring MVC to call our method when the HTTP method is DELETE.
    // Its URL has a named chunk delimited with curly braces.
    // It represents a variable chunk. Its value can be almost anything other than "/" and will match a URL as long as the rest of the URL matches.
    //Our method doesn't return a type. It's void. That's fine with Spring MVC.
    // It omits the HTTP response body and returns 200 OK by default.
    //@ResponseStatus(HttpStatus.NO_CONTENT) overrides the default and returns a 204 No Content status for every request.
    // If we want something else, this approach would be too rigid.
    //@PathVariable tells Spring MVC to find the parameter in the URL. In this case, it's the variable chunk {id}.
    // The parameter's name must match the chunk's name.
    @DeleteMapping("/resource/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        // This is where we would use our id to delete.
    }
}
