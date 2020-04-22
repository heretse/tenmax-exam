package com.tenmax.service.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.tenmax.service.common.DeserializeJson;
import com.tenmax.service.common.HttpResponseMessage;
import com.google.gson.JsonObject;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SampleApi{ 
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
     * The path of API is http://host:port/sample/{id}?token=xxx
     * <p>
     * Return Json format message with 'Hello world' 
     * <p>
     * @param id This is example id. The Type is Integer.
     * @param token This is example token. The Type is String.
     * @return Output Json message
     */
    @GetMapping(value = "/sample/{id}")
    public  HashMap<String, Object> sayHello(@PathVariable("id") Integer id, @RequestParam(name = "token", required = false) String token){
        HttpResponseMessage respMsg = new HttpResponseMessage();

        respMsg.addResponseCode("000");
        respMsg.addResponseMsg("Hello world");
        respMsg.appendFieldAsString("Token", token);
        respMsg.appendFieldAsString("id", String.format("%d", id));
        return respMsg.toMap();
    }

    /**
     * The is example API with POST method
     * <p>
     * @param parameter Http request body with Json data
     * @return Output Json message 
     */
    @PostMapping(value = "/sample", consumes ="application/json")
    public HashMap<String, Object> postSample(@RequestBody String parameter){
        JsonObject jobj = DeserializeJson.toObject(parameter);

        String id = jobj.get("id").getAsString();

        HttpResponseMessage respMsg = new HttpResponseMessage();
        respMsg.addResponseCode("000");
        respMsg.addResponseMsg(String.format("Receive posted parameter id:%s", id));

        return respMsg.toMap();
    }

    /**
     * This API can upload file to server and then show file name and size
     * <p>
     * @param file Upload file
     * @param request Http request body with Json data
     * @return Output Json message
     */
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public HashMap<String, Object> upload(@RequestParam("file") MultipartFile  file, HttpServletRequest request){
        HttpResponseMessage respMsg = new HttpResponseMessage();
        
        respMsg.addResponseCode("000");
        respMsg.addResponseMsg(String.format("File name:%s, file size:%d", file.getOriginalFilename(), file.getSize()));
        return respMsg.toMap();
    }

    /**
     * The is example API with DELETE method
     * <p>
     * @param parameter Http request body with Json data
     * @return Output Json message
     */
    @DeleteMapping(value = "/sample", consumes = "application/json")
    public HashMap<String, Object> deleteSample(@RequestBody String parameter){
        JsonObject jobj = DeserializeJson.toObject(parameter);

        String id = jobj.get("id").getAsString();

        HttpResponseMessage respMsg = new HttpResponseMessage();
        respMsg.addResponseCode("000");
        respMsg.addResponseMsg(String.format("Delete item by id(%s)", id));

        return respMsg.toMap();
    }
}
