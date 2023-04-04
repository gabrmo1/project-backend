package br.com.project.controllers;

import br.com.project.dtos.TestDto;
import br.com.project.services.TestService;
import br.com.project.util.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public List<TestDto> findAll(@PageableDefault(sort = "test") Pageable page) {
        return testService.findAll(page);
    }

    @GetMapping(value = "/findByAnyAttributes",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public List<TestDto> findByAnyAttributes(@RequestBody TestDto testDto) throws ParseException {
        return testService.findByAnyAttributes(testDto);
    }

    @GetMapping(value = "/{oid}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public TestDto findByOid(@PathVariable("oid") String oid) {
        return testService.findByOid(oid);
    }

    @GetMapping(value = "/find",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public TestDto findByAttributes(@RequestBody TestDto testDto) {
        return testService.findByTest(testDto);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public TestDto create(@RequestBody TestDto testDto) {
        return testService.create(testDto);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public TestDto update(@RequestBody TestDto testDto) {
        return testService.update(testDto);
    }

    @DeleteMapping(value = "/{oid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("oid") String oid) {
        testService.delete(oid);
    }

}
