package br.com.project.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.modelmapper.internal.util.Objects;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
public class DefaultEntityImpl {

    @Id
    @Column(updatable = false, length = 38, nullable = false, unique = true)
    private String oid = generateOid();

    @Column(nullable = false)
    private Date creationDate = generateCreationDate();

    @Column(name = "creation_user_oid", nullable = false, length = 38, updatable = false)
    private String creationUserOid = generateCreationUserOid();

    private String generateOid() {
        return Objects.firstNonNull(this.oid, UUID.randomUUID().toString());
    }

    private Date generateCreationDate() {
        return Objects.firstNonNull(this.creationDate, new Date());
    }

    private String generateCreationUserOid() {
        SecurityContext context = SecurityContextHolder.getContext();

        if (context != null) {
            Authentication authentication = context.getAuthentication();

            if (authentication != null) {
                Object principal = authentication.getPrincipal();

                if (principal != null) {
                    if (principal instanceof User) {
                        return ((User) principal).getOid();
                    }
                }
            }
        }

        return null;
    }

}
