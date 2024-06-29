package com.gustavo.admin.catalogo.domain.validation;


public abstract class Validator {
    private final ValidationHandler handler;

    protected Validator(ValidationHandler handler) {
        this.handler = handler;
    }

    public abstract void validate();

    protected ValidationHandler validatorHandler(){
        return this.handler;
    }
}
