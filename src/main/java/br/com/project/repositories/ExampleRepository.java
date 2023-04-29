package br.com.project.repositories;

import br.com.project.entities.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ExampleRepository extends JpaRepository<ExampleEntity, String> {

    @Query(value = "SELECT ee from ExampleEntity ee where ee.test = :test")
    Optional<ExampleEntity> findByTest(String test);

}
