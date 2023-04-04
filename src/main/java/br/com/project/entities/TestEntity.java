package br.com.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "test")
public class TestEntity extends DefaultEntityImpl {

    private String test;

}
