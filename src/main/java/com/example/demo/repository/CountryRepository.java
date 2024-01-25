package com.example.demo.repository;

import com.example.demo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("select cou from Country cou where cou.capital.name = :capitalName")
    List<Country> getCountriesByCapitalNameJpa(@Param("capitalName") String capitalName);

    @Query(value = "SELECT cou.* FROM country as cou JOIN capital as cap ON cou.capital_id = cap.id WHERE cap.name = :capitalName",
            nativeQuery = true)
    List<Country> getCountriesByCapitalNameSql(@Param("capitalName") String capitalName);

}
