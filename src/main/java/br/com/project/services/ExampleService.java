package br.com.project.services;

import br.com.project.dtos.ExampleDto;
import br.com.project.entities.ExampleEntity;
import br.com.project.exceptions.ResourceNotFoundException;
import br.com.project.repositories.ExampleCustomRepository;
import br.com.project.repositories.ExampleRepository;
import br.com.project.util.ClassMapper;
import br.com.project.util.example.ExampleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExampleService extends ExampleUtils {

    private final ExampleRepository repository;
    private final ExampleCustomRepository customRepository;

    public List<ExampleDto> findAll(Pageable pageable) {
        List<ExampleEntity> examplesEntity = repository.findAll(pageable).getContent();
        List<ExampleDto> examples = ClassMapper.parseListObject(configuredMapper(), examplesEntity, ExampleDto.class);

        implementFindByOidHATEOAS(examples);

        return examples;
    }

    public List<ExampleDto> findByAnyAttributes(ExampleDto exampleDto) throws ParseException {
        List<ExampleEntity> examplesEntity = customRepository.findByAnyAttributes(exampleDto);
        List<ExampleDto> examples = ClassMapper.parseListObject(configuredMapper(), examplesEntity, ExampleDto.class);

        implementFindByOidHATEOAS(examples);

        return examples;
    }

    public ExampleDto findByOid(String oid) {
        ExampleEntity exampleEntity = repository.findById(oid).orElseThrow(() -> new ResourceNotFoundException("No records found for this registration key"));

        return ClassMapper.parseObject(configuredMapper(), exampleEntity, ExampleDto.class);
    }

    public ExampleDto findByTest(ExampleDto exampleDto) {
        ExampleEntity entity = repository.findByTest(exampleDto.getTest()).orElseThrow(() -> new ResourceNotFoundException("No records found for this test"));

        exampleDto = ClassMapper.parseObject(configuredMapper(), entity, ExampleDto.class);
        implementFindByOidHATEOAS(exampleDto);

        return exampleDto;
    }

    @Transactional
    public ExampleDto create(ExampleDto exampleDto) {
        ExampleEntity exampleEntity = repository.save(setAllPropertiesOnEntityFromDTO(null, exampleDto));

        exampleDto = ClassMapper.parseObject(configuredMapper(), exampleEntity, ExampleDto.class);
        implementFindByOidHATEOAS(exampleDto);

        return exampleDto;
    }

    @Transactional
    public ExampleDto update(ExampleDto exampleDto) {
        ExampleEntity exampleEntity = repository.findById(exampleDto.getRegistrationKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this registration key"));

        repository.save(setAllPropertiesOnEntityFromDTO(exampleEntity, exampleDto));

        exampleDto = ClassMapper.parseObject(configuredMapper(), exampleEntity, ExampleDto.class);
        implementFindByOidHATEOAS(exampleDto);

        return exampleDto;
    }

    @Transactional
    public void delete(String oid) {
        ExampleEntity exampleEntity = repository.findById(oid).orElseThrow(() -> new ResourceNotFoundException("No records found for this registration key"));

        repository.delete(exampleEntity);
    }

}
