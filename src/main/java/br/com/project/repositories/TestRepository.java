package br.com.project.repositories;

import br.com.project.entities.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TestRepository extends JpaRepository<TestEntity, String> {

    @Query(value = "SELECT t from TestEntity t where t.test = :test")
    Optional<TestEntity> findByTest(String test);

}
