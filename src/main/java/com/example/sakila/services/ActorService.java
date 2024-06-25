package com.example.sakila.services;

import com.example.sakila.entities.Actor;
import com.example.sakila.input.ActorInput;
import com.example.sakila.output.ActorDetailsOutput;
import com.example.sakila.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    @Autowired
    ActorRepository actorRepository;

    public List<ActorDetailsOutput> getActors(int page,int size){
        return actorRepository.findAll(PageRequest.of(page,size)).stream().map(ActorDetailsOutput::new).toList();
    }

    public List<ActorDetailsOutput> findActorsByFirstName(String name, int page, int size){
        System.out.println(name);
        return actorRepository.findByFirstName(name, PageRequest.of(page,size)).stream().map(ActorDetailsOutput::new).toList();
    }
    public List<ActorDetailsOutput> findActorsByLastName(String lastName, int page ,int size){

        return actorRepository.findByLastNameContainingIgnoreCase(lastName, PageRequest.of(page,size)).stream().map(ActorDetailsOutput::new).toList();
    }




    public Optional<ActorDetailsOutput> getActor(short id){
        Optional<Actor> optionalActor = actorRepository.findById(id);
        if(!optionalActor.isPresent())
            return Optional.empty();

        return Optional.of(new ActorDetailsOutput(optionalActor.get()));
    }

    public Actor createaActor(ActorInput data){
        Actor actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getFirstName());

        actor = actorRepository.save(actor);

        return actor;
    }


    public void deleteActor(short id){
        Optional<Actor> actor = actorRepository.findById(id);
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
