package br.com.project.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
public class TestDto extends RepresentationModel<TestDto> {

    private String registrationKey;
    private String creationDate;
    private String test;

}
