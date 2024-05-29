package com.postgresql.ytdemo;

import com.postgresql.ytdemo.model.Person;
import com.postgresql.ytdemo.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    PersonRepo repo;

    @GetMapping
    public ResponseEntity<List<Person>> getPeople() {
        List<Person> people = repo.findAll();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        var person = repo.findById(id).orElse(null);
        if (person == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        if (person == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        repo.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        var existingPerson = repo.findById(id).orElse(null);
        if (existingPerson == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        existingPerson.setUsername(person.getUsername());
        existingPerson.setPassword(person.getPassword());
        existingPerson.setName(person.getName());

        var updatedPerson = repo.save(existingPerson);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletePerson(@PathVariable Long id) {
        var person = repo.findById(id).orElse(null);
        if (person == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repo.delete(person);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
