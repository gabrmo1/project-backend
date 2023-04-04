package br.com.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NoPermissionForThat extends RuntimeException {

    public NoPermissionForThat(String ex) {
        super(ex);
    }

}
