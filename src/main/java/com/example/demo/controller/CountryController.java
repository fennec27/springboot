package com.example.demo.controller;

import com.example.demo.entity.Country;
import com.example.demo.entity.CountryCapital;
import com.example.demo.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("country")
@AllArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping(value = "", produces = "application/json")
    public List<Country> getAll(@RequestParam(required = false) String capitalName, @RequestParam(required = false) Boolean useSql) {
        if (capitalName==null || capitalName.isEmpty()) {
            return countryService.getAll();
        } else {
            if (useSql != null && useSql) {
                return countryService.getCountriesByCapitalNameSql(capitalName);
            } else {
                return countryService.getCountriesByCapitalNameJpa(capitalName);
            }
        }
    }

    @PostMapping(value="", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> Create(@RequestBody CountryCapital countrycapital){
        Optional<Country> c = countryService.Create(countrycapital);
        if (c.isPresent()) {
            return ResponseEntity.ok(c.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}
