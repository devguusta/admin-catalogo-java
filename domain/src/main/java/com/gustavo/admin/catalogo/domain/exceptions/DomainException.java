package com.gustavo.admin.catalogo.domain.exceptions;

import com.gustavo.admin.catalogo.domain.validation.Error;

import java.util.List;

public class DomainException extends  NoStackTraceException {

    private final List<Error> errors;

    private DomainException(final String message, final List<Error> errors){

        super(message);
        this.errors = errors;
    }

    public static DomainException with(final List<Error> errors){
        return new DomainException("",errors);
    }

    public static DomainException with(Error error){
        return new DomainException(error.message(), List.of(error));
    }

    public List<Error> getErrors(){
        return errors;
    }
}
