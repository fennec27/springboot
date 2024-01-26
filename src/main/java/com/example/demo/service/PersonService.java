package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PersonService
{
    private final PersonRepository personRepository;

    // create, read, update, delete

    public Person Create(Person person)
    {
        return personRepository.save(person);
    }

    public List<Person> GetAll(){
        return personRepository.findAll();
    }

    public Optional<Person> GetOne(Long id){
        return personRepository.findById(id);
    }

    public Optional<Person> Patch(Long id, Person person){
        Optional<Person> p = personRepository.findById(id);
        if (p.isPresent()) {
            Person pOld = p.get();
            if (person.getFirstName()!=null && !person.getFirstName().isEmpty()) {
                pOld.setFirstName(person.getFirstName());
            }
            if (person.getLastName()!=null && !person.getLastName().isEmpty()) {
                pOld.setLastName(person.getLastName());
            }
            return Optional.of(personRepository.save(pOld));
        }
        return Optional.empty();
    }

    public boolean Delete(Long id){
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Person AddAddress(Long id, Address address){
        Optional<Person> p = personRepository.findById(id);
        if (p.isPresent()) {
            Person person = p.get();
            address.setPerson(person);
            person.getAddresses().add(address);
            return personRepository.save(person);
        }
        return null;
    }



}
