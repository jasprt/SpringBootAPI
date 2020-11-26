package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
public class HelloController {

    AtomicInteger integer = new AtomicInteger(1);
    HashMap<String, Integer> hm = new HashMap<>();

    @RequestMapping("/")
    public String getHome() {
        return "Hello Welcome to SpringBootAPI!";
    }

    @RequestMapping("/rest")
    public String getHello() {
        return "Hello World!";
    }

    @RequestMapping("/html")
    public String getHtml() {
        return "<h1> hello World!</h1>";
    }

    @RequestMapping(path = "/greeting", produces = MediaType.APPLICATION_JSON_VALUE)
    public Greeting getGreeting(@RequestParam(value = "name") String name) {
        return new Greeting(name, integer.incrementAndGet());
    }

    @RequestMapping(path = "/commit")
    public String commit(@RequestParam(value = "name") String name,
                         @RequestParam(value = "salary") int salary) {

        try {
            hm.put(name, salary);
        } catch (Exception e) {
            System.out.println("Could not commit!");
        }

        return "Commit successful for: " + name+" with salary: "+salary;

    }

    @RequestMapping(path = "/fetch")
    public String fetchSalary(@RequestParam(value = "name") String name) {
        return "Salary for "+name+" is: "+hm.get(name);
    }

    @RequestMapping(path = "/fetchall")
    public String fetchall() {
        StringBuilder sb = new StringBuilder();
        hm.forEach((a, b) -> sb.append("\n Salary for "+a+" is: "+b));
        return sb.toString();
    }


}

class Greeting {

    private final String name;
    private final int userId;

    Greeting(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

}
