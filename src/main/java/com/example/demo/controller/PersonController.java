package com.example.demo.controller;

import com.example.demo.entity.Address;
import com.example.demo.entity.Book;
import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<?> getAll() {
        List<Person> l = personService.GetAll();
        if (l.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(l);
    }

    /*@GetMapping(value = "", produces = "application/json")
    public List<Person> getAll() {
        List<Person> l = personService.GetAll();
        if (l.isEmpty()) {
            return ResponseEntity
        }
        return l;
    }*/

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        Optional<Person> p = personService.GetOne(id);
        if (p.isPresent()) {
            return ResponseEntity.ok(p.get());
        } else {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erreur fma");
            return ResponseEntity.notFound().build();
        }
    }
/*
    @GetMapping(value = "{id}", produces = "application/json")
    public Person getOne(@PathVariable Long id) {
        Optional<Person> p = personService.GetOne(id);
        if (p.isPresent()) {
            return p.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
 */

    @PostMapping(value="", consumes = "application/json", produces = "application/json")
    public Person Create(@RequestBody Person person){
        return personService.Create(person);
    }


    @DeleteMapping(value="{id}", produces = "application/json")
    public ResponseEntity<?> Delete(@PathVariable Long id){
        if (personService.Delete(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(value="{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> Patch(@RequestBody Person person, @PathVariable Long id){
        Optional<Person> p = personService.Patch(id, person);
        if (p.isPresent()) {
            return ResponseEntity.ok(p.get());
        } else {
            return ResponseEntity.badRequest().body("Ca ne marche pas");
        }
    }


    @PostMapping(value="{id}/address", consumes = "application/json", produces = "application/json")
    public Person AddAdress(@PathVariable Long id, @RequestBody Address address){
        Person p = personService.AddAddress(id, address);
        if (p != null) {
            return p;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="{id}/addresses", consumes = "application/json", produces = "application/json")
    public Person AddAdresses(@PathVariable Long id, @RequestBody List<Address> addresses){
        Optional<Person> p = personService.AddAddresses(id, addresses);
        if (p.isPresent()) {
            return p.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
