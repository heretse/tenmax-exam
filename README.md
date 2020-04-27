# tenmax-exam

[![Build Status](https://travis-ci.org/heretse/tenmax-exam.svg?branch=master)](https://travis-ci.org/heretse/tenmax-exam)
[![Code Coverage](https://img.shields.io/codecov/c/github/heretse/tenmax-exam/master.svg)](https://codecov.io/github/heretse/tenmax-exam?branch=master)

* Project Structure
```
└── src
    └── main
        └── java
            └── com.tenmax.exam
                └── com.tenmax.exam.common
                    └── DataType.java
                        DeserializeJson.java
                        HttpUtil.java
                └── com.tenmax.exam.Controller
                    └── AdvertiseApiController.java
                └── com.tenmax.exam.model
                    └── Advertise.java
                └── com.tenmax.exam.repo
                    └── AdvertisesRepository.java
                └── com.tenmax.exam.service
                    └── AdvertiseService.java
                        CronJobService.java
```

* Project Description

    - Scheduled by cron job for every minute to fetch data
    - Use CompletableFuture for asynchronous processing request
    ```
    @Scheduled(cron = "1 * * * * ?")
        public void run() {
            CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
                logger.debug("Current time in runAsync() :: " + Calendar.getInstance().getTime());
    
                String response = HttpUtil.get("https://tenmax-mock-dsp.azurewebsites.net/api/getAds?code=s9Ybtsb6hwigndO6a5OwsLmXOPR0olrW7nBFFE7QmHfvaQ6p9GWXwg==");
    
                logger.debug("response :: " + response);
    
                JsonObject jsonObject = DeserializeJson.toObject(response);
    
                Advertise newAdvertise = AdvertiseService.parseFromJsonObject(jsonObject);
    
                if (newAdvertise != null) {
                    advertisesRepository.save(newAdvertise);
                }
    
            });
    ```
* API Description
    - Get advertise by title and support wild card search
        * GET: /getAdsByTitle?title=?
    - Get random advertise data
        * GET: /getAds

* Integration Testing with Embedded MongoDB

    - Override the database configurations with Embedded MongoDB
    ```
    spring.data.mongodb.uri=mongodb://localhost:27017/embedded
    spring.data.mongodb.database=embedded
    ```
  
    - Use MockMvc to perform a GET request  
    ```
    mvc.perform(get("/getAdsByTitle?title=找工作")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content()
                            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.result.length()").value(2))
                    .andDo(result -> {
                        result.getResponse().setCharacterEncoding("UTF-8");
                        System.out.println(result.getResponse().getContentAsString());
                    });
    ```
  
* Build docker image
    - Build docker image by executing shellscript
    ```
    $./docker_docker.sh
    ```