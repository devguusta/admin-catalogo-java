package com.gustavo.admin.catalogo.domain.entities;

import com.gustavo.admin.catalogo.domain.validation.ValidationHandler;
import com.gustavo.admin.catalogo.domain.valueobject.Identifier;

import java.util.Objects;

public  abstract   class Entity<ID extends Identifier> {
    protected final ID id;

    public ID getId() {
        return id;
    }

    public Entity(ID id) {
        Objects.requireNonNull(id, "id should not be null");
        this.id = id;
    }


        public abstract   void validate(ValidationHandler handler);

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return getId().equals(entity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
