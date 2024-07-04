package com.gustavo.admin.catalogo.domain.validation.handler;

import com.gustavo.admin.catalogo.domain.exceptions.DomainException;
import com.gustavo.admin.catalogo.domain.validation.Error;
import com.gustavo.admin.catalogo.domain.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification  implements ValidationHandler {

    private final List<Error> errors;

    private Notification(List<Error> errors) {
        this.errors = errors;
    }

    public static Notification create() {
        return new Notification(new ArrayList<>());
    }

    public static Notification create(Error error) {
        return new Notification(new ArrayList<>()).append(error);
    }
    public static Notification create(final Throwable throwable) {
       return create(new Error(throwable.getMessage()));
    }

    @Override
    public Notification append(Error error) {
        errors.add(error);
        return this;
    }

    @Override
    public Notification append(ValidationHandler handler) {
        errors.addAll(handler.getErrors());
        return this;

    }

    @Override
    public Notification validate(Validation validation) {
        try {
            validation.validate();
        } catch (final DomainException ex){
            this.errors.addAll(ex.getErrors());
        }


        catch (final Throwable e) {
           append(new Error(e.getMessage()));
        }

        return this;
    }

    @Override
    public List<Error> getErrors() {
      return errors;
    }
}
