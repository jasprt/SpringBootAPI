package com.example.demo.controller;

import com.example.demo.City;
import com.example.demo.database.RDSProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class CityController {

    @RequestMapping(value = "/viewcity", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getCities() throws Exception {
        ArrayList<City> cities = RDSProcessor.getInstance().queryCity("select * from city");
        HashMap<String, Object> hmap = new HashMap<>();
        hmap.put("Cities", cities);
        return new ResponseEntity<>(hmap, HttpStatus.OK);
    }

    @RequestMapping(value = "/getCity", produces = MediaType.APPLICATION_XML_VALUE)
    public City getCity(@RequestParam(value = "city") String city) throws Exception {
        return RDSProcessor.getInstance().queryCity("select * from city where Name = '"+city+"'").get(0);
    }
}
