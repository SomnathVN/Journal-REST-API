package com.example.jounralrestapi.controller;

import com.example.jounralrestapi.api.response.WeatherResponse;
import com.example.jounralrestapi.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.jounralrestapi.entity.User;
import com.example.jounralrestapi.repository.UserRepository;
import com.example.jounralrestapi.service.UserService;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewEntry(userInDb);
            return new ResponseEntity<>(userInDb,HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteByUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<?> greeting(@PathVariable String city){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather(city);
        String greetings = "";
        if(weatherResponse!=null){
            greetings = " temparature feels like "+weatherResponse.getMain().getFeelsLike();
        }
        return  new ResponseEntity<>("Hii "+authentication.getName()+greetings,HttpStatus.OK);
    }
}
