package br.com.project.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExampleDto extends RepresentationModel<ExampleDto> {

    private String registrationKey;
    private String creationDate;
    private String test;

}
