package com.example.employeeservice.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilterConfiguration {

    @GetMapping("/filter1")
    public MappingJacksonValue getAllUserDetails(){
        List<UserDetails> details = UserDetails.getUserDetails();
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userId","userName");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserDetails",filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(details);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
    @GetMapping("/filter2")
    public MappingJacksonValue getAllPanUserDetails(){
        List<UserDetails> details = UserDetails.getUserDetails();
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("panNumber","userName");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserDetails",filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(details);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
//@JsonIgnoreProperties(value = {"panNumber"})
class UserDetails{
    private int userId;
    private String userName;
    private  String panNumber;

    public UserDetails(int userId, String userName, String panNumber) {
        this.userId = userId;
        this.userName = userName;
        this.panNumber = panNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
    public static List<UserDetails> getUserDetails(){
        return Arrays.asList(new UserDetails(1,"Arif","KNMAKL"),
                new UserDetails(2,"ABC","POLOJHSA"),
                new UserDetails(3,"PQR","POOIYYD"));

    }
}