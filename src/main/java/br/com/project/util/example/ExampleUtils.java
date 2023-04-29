package br.com.project.util.example;

import br.com.project.controllers.ExampleController;
import br.com.project.dtos.ExampleDto;
import br.com.project.entities.ExampleEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Objects;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ExampleUtils {

    protected void implementFindByOidHATEOAS(ExampleDto exampleDto) {
        exampleDto.add(linkTo(methodOn(ExampleController.class).findByOid(exampleDto.getRegistrationKey())).withSelfRel());
    }

    protected void implementFindByOidHATEOAS(List<ExampleDto> examples) {
        examples.forEach(this::implementFindByOidHATEOAS);
    }

    protected ModelMapper configuredMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(ExampleEntity.class, ExampleDto.class).addMappings(mapping -> mapping.map(ExampleEntity::getOid, ExampleDto::setRegistrationKey));

        return mapper;
    }

    protected ExampleEntity setAllPropertiesOnEntityFromDTO(ExampleEntity exampleEntity, ExampleDto exampleDto) {
        exampleEntity = Objects.firstNonNull(exampleEntity, new ExampleEntity());

        exampleEntity.setTest(exampleDto.getTest());

        return exampleEntity;
    }

}
