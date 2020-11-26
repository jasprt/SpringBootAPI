package com.example.demo.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movie")
public class MovieCatalog {

    @RequestMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieDetail getDetail(@PathVariable("userId") String userId) {
        return new MovieDetail("Titanic", 9, 1995);
    }
}
