package br.com.project.services;

import br.com.project.dtos.TestDto;
import br.com.project.entities.TestEntity;
import br.com.project.exceptions.ResourceNotFoundException;
import br.com.project.repositories.TestCustomRepository;
import br.com.project.repositories.TestRepository;
import br.com.project.util.ClassMapper;
import br.com.project.util.test.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService extends TestUtils {

    private final TestRepository repository;
    private final TestCustomRepository customRepository;

    public List<TestDto> findAll(Pageable pageable) {
        List<TestEntity> testEntities = repository.findAll(pageable).getContent();
        List<TestDto> testDtos = ClassMapper.parseListObject(configuredMapper(), testEntities, TestDto.class);

        implementFindByOidHATEOAS(testDtos);

        return testDtos;
    }

    public List<TestDto> findByAnyAttributes(TestDto testDto) throws ParseException {
        List<TestEntity> testEntities = customRepository.findByAnyAttributes(testDto);
        List<TestDto> testDtos = ClassMapper.parseListObject(configuredMapper(), testEntities, TestDto.class);

        implementFindByOidHATEOAS(testDtos);

        return testDtos;
    }

    public TestDto findByOid(String oid) {
        TestEntity testEntity = repository.findById(oid).orElseThrow(() -> new ResourceNotFoundException("No records found for this registration key"));

        return ClassMapper.parseObject(configuredMapper(), testEntity, TestDto.class);
    }

    public TestDto findByTest(TestDto testDto) {
        TestEntity entity = repository.findByTest(testDto.getTest()).orElseThrow(() -> new ResourceNotFoundException("No records found for this test"));

        testDto = ClassMapper.parseObject(configuredMapper(), entity, TestDto.class);
        implementFindByOidHATEOAS(testDto);

        return testDto;
    }

    @Transactional
    public TestDto create(TestDto testDto) {
        TestEntity testEntity = repository.save(setAllPropertiesOnEntityFromDTO(null, testDto));

        testDto = ClassMapper.parseObject(configuredMapper(), testEntity, TestDto.class);
        implementFindByOidHATEOAS(testDto);

        return testDto;
    }

    @Transactional
    public TestDto update(TestDto testDto) {
        TestEntity testEntity = repository.findById(testDto.getRegistrationKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this registration key"));

        repository.save(setAllPropertiesOnEntityFromDTO(testEntity, testDto));

        testDto = ClassMapper.parseObject(configuredMapper(), testEntity, TestDto.class);
        implementFindByOidHATEOAS(testDto);

        return testDto;
    }

    @Transactional
    public void delete(String oid) {
        TestEntity testEntity = repository.findById(oid).orElseThrow(() -> new ResourceNotFoundException("No records found for this registration key"));

        repository.delete(testEntity);
    }

}
