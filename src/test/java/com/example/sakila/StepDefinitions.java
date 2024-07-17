package com.example.sakila;

import com.example.sakila.controllers.ActorController;
import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.output.ActorDetailsOutput;
import com.example.sakila.support.EndPoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.cucumber.java.en.*;

import io.cucumber.java.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
public class StepDefinitions {

    ActorController c = new ActorController();
    List<ActorDetailsOutput> list= new ArrayList<>();
    ResponseEntity<Object[]> listReponse;
    ResponseEntity<Object> itemReponse;

    RestTemplate restTemplate ;
    ObjectMapper mapper ;
    int lastResponseCode = 0;
    @Before
    public void setup(){
        restTemplate = new RestTemplate();
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

    }


    @Given("the server is running")
    public void theServerIsRunning() {
        String url = "http://localhost:8080/greeting";
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        assertEquals(response.getStatusCode().value(),200);
    }

    @When("I send a GET request to {endpoint}")
    public void iSendAGETRequestTo(EndPoint endpoint) {
        String url = "http://localhost:8080" + endpoint.full();
        if(endpoint.id==null) {
            listReponse = restTemplate.getForEntity(url, Object[].class);
            lastResponseCode = listReponse.getStatusCode().value();
        }
        else {
            itemReponse = restTemplate.getForEntity(url, Object.class);
            lastResponseCode = itemReponse.getStatusCode().value();
        }

    }
    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(Integer statusCode) {
        assertEquals(lastResponseCode,statusCode.intValue());
    }
    @Then("the response should contain at most {int} actors")
    public void theResponseShouldContainAtMostActors(Integer ActorCount) throws JsonProcessingException {
        List<Actor> actors = Arrays.stream(listReponse.getBody()).map(object->mapper.convertValue(object,Actor.class)).collect(Collectors.toList());
        assertEquals(actors.size()<= ActorCount.intValue(),true);

    }

    @And("the actor should have a non empty first and last name")
    public void theActorShouldHaveANonEmptyFirstAndLastName() {
        Actor actor =  mapper.convertValue(itemReponse.getBody(),Actor.class);
        assertEquals(!actor.getFirstName().isEmpty() && !actor.getLastName().isEmpty() ,true);
    }


//    @When("I send a GET request to films {endpoint}")
//    public void iSendAGETRequestToFilms(EndPoint endPoint) {
//        String url = "http://localhost:8080" + endPoint.full();
//        if(endPoint.id==null) {
//            filmsReponse = restTemplate.getForEntity(url, Object[].class);
//            lastResponseCode = filmsReponse.getStatusCode().value();
//        }
//        else {
//            filmReponse = restTemplate.getForEntity(url, Object.class);
//            lastResponseCode = filmReponse.getStatusCode().value();
//        }
//
//    }

    @And("the response should contain at most {int} films")
    public void theResponseShouldContainAtMostFilms(Integer filmCount) {
        List<Film> films = Arrays.stream(listReponse.getBody()).map(object->mapper.convertValue(object, Film.class)).collect(Collectors.toList());
        assertEquals(films.size()<= filmCount.intValue(),true);

    }

    @And("the film should have a non empty title")
    public void theFilmShouldHaveANonEmptyTitle() {
        Film film =  mapper.convertValue(itemReponse.getBody(),Film.class);
        assertEquals(!film.getTitle().isEmpty() ,true);

    }
}
