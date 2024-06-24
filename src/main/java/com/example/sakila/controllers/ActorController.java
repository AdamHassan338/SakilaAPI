package com.example.sakila.controllers;

import com.example.sakila.entities.Actor;
import com.example.sakila.input.ActorInput;
import com.example.sakila.input.ValidationGroup;
import com.example.sakila.output.ActorDetailsOutput;
import com.example.sakila.repository.ActorRepository;
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
    private ActorRepository actorRepository;

    @GetMapping
    public List<ActorDetailsOutput> getActors(){
        return actorRepository.findAll().stream().map(ActorDetailsOutput::new).toList();
    }

    @GetMapping("/{id}")
    public ActorDetailsOutput getActorByID(@PathVariable Short id){
        return actorRepository.findById(id).map(ActorDetailsOutput::new).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("No actor with id: %d",id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActorDetailsOutput createActor(@Validated(ValidationGroup.Create.class) @RequestBody ActorInput data){
        Actor actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());

        actor = actorRepository.save(actor);
        return new ActorDetailsOutput(actor);
        //return ResponseEntity.status(HttpStatus.CREATED).body(actor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteActor(@PathVariable Short id){
        Optional<Actor> actor = actorRepository.findById(id);
        if(actor.isPresent())
            actorRepository.delete(actor.get());

    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ActorDetailsOutput> updateActor(@PathVariable Short id, @Validated(ValidationGroup.Update.class) @RequestBody ActorInput data){
        Optional<Actor> actor = actorRepository.findById(id);
        if(!actor.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        Actor found = actor.get();
        if(data.getFirstName()!=null)
            found.setFirstName(data.getFirstName());
        if(data.getLastName()!=null)
            found.setLastName(data.getLastName());
        actorRepository.save(found);

        return ResponseEntity.status(HttpStatus.OK).body(new ActorDetailsOutput(found));
    }


}
