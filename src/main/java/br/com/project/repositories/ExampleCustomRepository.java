package br.com.project.repositories;

import br.com.project.dtos.ExampleDto;
import br.com.project.entities.ExampleEntity;
import br.com.project.util.QueryBuilder;
import br.com.project.util.Validator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;

@Repository
public class ExampleCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<ExampleEntity> findByAnyAttributes(ExampleDto exampleDto) throws ParseException {
        QueryBuilder<ExampleEntity> query = new QueryBuilder<>(entityManager);

        query.select("ee")
                .from("ExampleEntity ee");

        if (Validator.stringIsValid(exampleDto.getRegistrationKey())) {
            query.where("ee.oid = :oid");
        } else {
            if (exampleDto.getCreationDate() != null) {
//TODO test all scenarios
                query.where("CAST (ee.creationDate AS STRING) LIKE :creationDate")
                        .paramDate("creationDate", exampleDto.getCreationDate());
            }
            if (Validator.stringIsValid(exampleDto.getTest())) {
                query.where("ee.test = :test")
                        .param("test", exampleDto.getTest());
            }
        }

        return query.getAllResults();
    }

}
