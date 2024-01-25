package com.example.demo.service;

import com.example.demo.entity.Country;
import com.example.demo.repository.CountryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public List<Country> getCountriesByCapitalNameJpa(String capitalName) {
        System.out.println("Using JPA");
        return countryRepository.getCountriesByCapitalNameJpa(capitalName);
    }

    public List<Country> getCountriesByCapitalNameSql(String capitalName) {
        System.out.println("Using SQL");
        return countryRepository.getCountriesByCapitalNameSql(capitalName);
    }

}
