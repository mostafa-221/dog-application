package com.example.dbexample.repo;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DogsRepository extends CrudRepository<Dog, Long> {

    @Query(value = "select id, name, age from dog where age > 10 order by name", nativeQuery = true)
    List<Dog> findOldDogs();

    @Modifying
    @Transactional
    @Query(value = "delete from dog where name=:name", nativeQuery = true)
    void deleteByName(@Param("name") String name);

    Object findAll(Sort name);

}
