package br.com.project.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.internal.util.Objects;

import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
public class AuthImpl {

    @Id
    @Column(updatable = false, length = 38, nullable = false, unique = true)
    private String oid = generateOid();

    @Column(nullable = false)
    private Date creationDate = generateCreationDate();

    private String generateOid() {
        return Objects.firstNonNull(this.oid, UUID.randomUUID().toString());
    }

    private Date generateCreationDate() {
        return Objects.firstNonNull(this.creationDate, new Date());
    }

}
