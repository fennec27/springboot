package com.example.demo.service;

import com.example.demo.entity.Capital;
import com.example.demo.entity.Country;
import com.example.demo.entity.CountryCapital;
import com.example.demo.repository.CapitalRepository;
import com.example.demo.repository.CountryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CapitalRepository capitalRepository;

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public List<Country> getCountriesByCapitalNameJpa(String capitalName) {
        System.out.println("Using JPA");
        return countryRepository.getCountriesByCapitalNameJpa(capitalName);
    }

    public List<Country> getCountriesByCapitalNameSql(String capitalName) {
        System.out.println("Using SQL");
        return countryRepository.getCountriesByCapitalNameSql(capitalName);
    }

    public Optional<Country> Create(CountryCapital countrycapital){

        Capital capital = new Capital();
        capital.setName(countrycapital.getCapital().getName());
        capital.setPopulation(countrycapital.getCapital().getPopulation());
        capital = capitalRepository.save(capital);

        Country country = new Country();
        country.setName(countrycapital.getCountry().getName());
        country.setPopulation(countrycapital.getCountry().getPopulation());
        country.setCapital(capital);

        return Optional.of(countryRepository.save(country));

    }


}
