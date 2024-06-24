package com.example.sakila.services;

import com.example.sakila.entities.Actor;
import com.example.sakila.input.ActorInput;
import com.example.sakila.output.ActorDetailsOutput;
import com.example.sakila.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    @Autowired
    ActorRepository actorRepository;

    public List<Actor> getActors(){
        return actorRepository.findAll();
    }


    public Optional<Actor> getActor(short id){
        return actorRepository.findById(id);
    }

    public Actor createaActor(ActorInput data){
        Actor actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getFirstName());

        actor = actorRepository.save(actor);

        return actor;
    }


    public void deleteActor(short id){
        Optional<Actor> actor = getActor(id);
        if(actor.isPresent())
            actorRepository.delete(actor.get());

    }

    public Optional<ActorDetailsOutput> updateActor(Short id, ActorInput data){
        Optional<Actor> optionalActor = actorRepository.findById(id);
        if(!optionalActor.isPresent())
            return Optional.empty();
        Actor found = optionalActor.get();
        if(data.getFirstName()!=null)
            found.setFirstName(data.getFirstName());
        if(data.getLastName()!=null)
            found.setLastName(data.getLastName());
        actorRepository.save(found);
        return Optional.of(new ActorDetailsOutput(found));
    }
}
