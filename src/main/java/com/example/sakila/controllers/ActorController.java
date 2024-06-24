package com.example.sakila.controllers;

import com.example.sakila.entities.Actor;
import com.example.sakila.input.ActorInput;
import com.example.sakila.input.ValidationGroup;
import com.example.sakila.output.ActorDetailsOutput;
import com.example.sakila.repository.ActorRepository;
import com.example.sakila.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping
    public List<ActorDetailsOutput> getActors(){
        return actorService.getActors().stream().map(ActorDetailsOutput::new).toList();
    }

    @GetMapping("/{id}")
    public ActorDetailsOutput getActorByID(@PathVariable Short id){
        Optional<Actor> optionalActor = actorService.getActor(id);
        if(optionalActor.isPresent()){
            return new ActorDetailsOutput(optionalActor.get());

        }else{
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("No actor with id: %d",id));
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActorDetailsOutput createActor(@Validated(ValidationGroup.Create.class) @RequestBody ActorInput data){



        Actor actor = actorService.createaActor(data);

        return new ActorDetailsOutput(actor);
        //return ResponseEntity.status(HttpStatus.CREATED).body(actor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteActor(@PathVariable Short id){
        actorService.deleteActor(id);

    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ActorDetailsOutput> updateActor(@PathVariable Short id, @Validated(ValidationGroup.Update.class) @RequestBody ActorInput data){
        Optional<ActorDetailsOutput> output = actorService.updateActor(id,data);
        if(!output.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);


        return ResponseEntity.status(HttpStatus.OK).body(output.get());
    }


}
