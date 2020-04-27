# tenmax-exam

[![Build Status](https://travis-ci.org/heretse/tenmax-exam.svg?branch=master)](https://travis-ci.org/heretse/tenmax-exam)
[![Code Coverage](https://img.shields.io/codecov/c/github/heretse/tenmax-exam/master.svg)](https://codecov.io/github/heretse/tenmax-exam?branch=master)

* Project Status
    - Basic Quiz
        * Parsing data from the specific API and insert into MongoDB - 100%
        * Implement a REST API supporting wildcard search by title   - 100%
    - Advanced Quiz
        * Implement a mock API with 10 seconds of latency - 100%
        * Parsing data from the specific API with CompletableFuture and 5 second of timeout - 100%  
    - Others
        * Integrated with Travis CI - 100%
        * Unit Test
            - Code coverage - See above badge
            - Integrate with CodeCov - 100% 
        * Integration Test
            - Test for the basic quiz with embedded MongoDB - 100%
            - Test for the advanced quiz - 30%
        * Support docker build - 100%  
        
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
        * Implement wildcard search by regular expression settings 
        ```
        @Repository
        public interface AdvertisesRepository extends MongoRepository<Advertise, String> {
            @Query("{title:{'$regex': ?0, '$options' : 'i'}}")
            public List<Advertise> findByTitle(String title);
        }
        ```
    - Get random advertise data
        * GET: /getAds
        * Implement request with 0 or 10 seconds of latency by Callable
        ```
        @GetMapping(value = "/getAds", produces = "application/json;charset=UTF-8")
            public Callable<String> echoHelloWorld()
            {
                boolean skip = (random.nextInt(2) == 0);
        
                return () ->
                {
                    if (skip) {
                        try {
                            Thread.sleep(10 * 1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                             return "{}";
                        }
                    } else {
                        return fakeData[random.nextInt(fakeData.length)];
                    }
                };
            }
        ``` 

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