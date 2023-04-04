package br.com.project.repositories;

import br.com.project.dtos.TestDto;
import br.com.project.entities.TestEntity;
import br.com.project.util.QueryBuilder;
import br.com.project.util.Validator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;

@Repository
public class TestCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<TestEntity> findByAnyAttributes(TestDto testDto) throws ParseException {
        QueryBuilder<TestEntity> query = new QueryBuilder<>(entityManager);

        query.select("te")
                .from("TestEntity te");

        if (Validator.stringIsValid(testDto.getRegistrationKey())) {
            query.where("te.oid = :oid");
        } else {
            if (testDto.getCreationDate() != null) {
//TODO test all scenarios
                query.where("CAST (te.creationDate AS STRING) LIKE :creationDate")
                        .paramDate("creationDate", testDto.getCreationDate());
            }
            if (Validator.stringIsValid(testDto.getTest())) {
                query.where("te.test = :test")
                        .param("test", testDto.getTest());
            }
        }

        return query.getAllResults();
    }

}
