package br.com.project.controllers;

import br.com.project.dtos.ExampleDto;
import br.com.project.services.ExampleService;
import br.com.project.util.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleService exampleService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public List<ExampleDto> findAll(@PageableDefault(sort = "test") Pageable page) {
        return exampleService.findAll(page);
    }

    @GetMapping(value = "/findByAnyAttributes",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public List<ExampleDto> findByAnyAttributes(@RequestBody ExampleDto exampleDto) throws ParseException {
        return exampleService.findByAnyAttributes(exampleDto);
    }

    @GetMapping(value = "/{oid}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ExampleDto findByOid(@PathVariable("oid") String oid) {
        return exampleService.findByOid(oid);
    }

    @GetMapping(value = "/find",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ExampleDto findByAttributes(@RequestBody ExampleDto exampleDto) {
        return exampleService.findByTest(exampleDto);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ExampleDto create(@RequestBody ExampleDto exampleDto) {
        return exampleService.create(exampleDto);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ExampleDto update(@RequestBody ExampleDto exampleDto) {
        return exampleService.update(exampleDto);
    }

    @DeleteMapping(value = "/{oid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("oid") String oid) {
        exampleService.delete(oid);
    }

}
