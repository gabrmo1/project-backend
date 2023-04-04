package br.com.project.util.test;

import br.com.project.controllers.TestController;
import br.com.project.dtos.TestDto;
import br.com.project.entities.TestEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Objects;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TestUtils {

    protected void implementFindByOidHATEOAS(TestDto testDto) {
        testDto.add(linkTo(methodOn(TestController.class).findByOid(testDto.getRegistrationKey())).withSelfRel());
    }

    protected void implementFindByOidHATEOAS(List<TestDto> testDtos) {
        testDtos.forEach(this::implementFindByOidHATEOAS);
    }

    protected ModelMapper configuredMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(TestEntity.class, TestDto.class).addMappings(mapping -> mapping.map(TestEntity::getOid, TestDto::setRegistrationKey));

        return mapper;
    }

    protected TestEntity setAllPropertiesOnEntityFromDTO(TestEntity testEntity, TestDto testDto) {
        testEntity = Objects.firstNonNull(testEntity, new TestEntity());

        testEntity.setTest(testDto.getTest());

        return testEntity;
    }

}
