package com.jpranin.calculator;

import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {

    @RequestMapping(value = "/hello")
    @ApiMethod(description = "Hello World endpoint")
    public String hello() {
        String string = "Hello World!";
		return string;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiMethod(description = "Adds any amount of numbers together")
    public float add(@RequestBody float[] numbers){
        float sum = 0;
        for(int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        if (sum - ((int) sum) == 0) {
            return (int) sum;
        }
        return sum;
    }

    @RequestMapping(value = "/prime/{value}", method = RequestMethod.GET)
    @ApiMethod(description = "Returns whether value is prime")
    public String isPrime(@ApiPathParam(name = "value") @PathVariable float value){
        
        String result = "";
        if (value - ((int) value) != 0) {
            result = value + " is not prime - it's a fraction";
        }
        else if (value == 2) {
            result = "2 is prime!";
        }
        else if (value % 2 == 0){
            result = (int) value + " is not prime - divisible by 2";
        }
        else {
            for(int i = 3; i * i <= value; i += 2){
                if (value % i == 0) {
                    result = (int) value + " is not prime - divisible by " + i;
                    break;
                }
            }
        }
        
        if (result.isEmpty()) {
            result = (int) value + " is prime!";
        }
        return result;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    @ApiMethod(description = "Generic Error page")
    public String error(){
        return "An error occured!";
    }

}
