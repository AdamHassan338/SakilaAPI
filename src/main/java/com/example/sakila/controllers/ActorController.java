package com.example.sakila.controllers;

import com.example.sakila.entities.Actor;
import com.example.sakila.input.ActorInput;
import com.example.sakila.input.ValidationGroup;
import com.example.sakila.output.ActorDetailsOutput;
import com.example.sakila.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping
    public List<ActorDetailsOutput> getActors(@RequestParam("firstName")  Optional<String> firstName,@RequestParam("lastName")  Optional<String> lastName,
                                              @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size",defaultValue = "10") int size){
        if(firstName.isPresent())
            return actorService.findActorsByFirstName(firstName.get().trim(),page,size);
        if(lastName.isPresent())
            return actorService.findActorsByLastName(lastName.get(),page,size);

        return actorService.getActors(page,size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDetailsOutput> getActorByID(@PathVariable Short id){
        Optional<ActorDetailsOutput> optionalOutput= actorService.getActor(id);
        if(optionalOutput.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND).body(optionalOutput.get());

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
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
