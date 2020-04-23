package com.tenmax.exam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tenmax.exam.model.Advertise;
import com.tenmax.exam.repo.AdvertisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleApi{

    @Autowired
    AdvertisesRepository advertisesRepository;

     /**
     * The path of API is http://host:port/hello
     * <p>
     * Return Json format message with 'Hello world' 
     * <p>
     * @return Output 'hello' message
     */
    @GetMapping(value = "/hello")
    public  String sayHello(){
        return "Hello";
    }

    /**
     * The path of API is http://host:port/getAdsByTitle?title=xxxxx
     * <p>
     * Return Json format message with 'Hello world' 
     * <p>
     * @param title This is a query by title. The Type is String.
     * @return Output Json message
     */
    @GetMapping(value = "/getAdsByTitle")
    public Map<String, Object> getAdsByTitle(@RequestParam(name = "title", required = true) String title) {

        List<Advertise> advertiseList = advertisesRepository.findByTitle(title);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", advertiseList);

        return map;
    }
}
