package com.gustavo.admin.catalogo.domain.category;

import com.gustavo.admin.catalogo.domain.valueobject.Identifier;

import java.util.Objects;
import java.util.UUID;

public class CategoryID extends Identifier {

    private final String value;


    private CategoryID(String value) {
        Objects.requireNonNull(value, "value should not be null");
        this.value = value;
    }

    public static CategoryID unique(){
        return  CategoryID.from(UUID.randomUUID());
    }



    public static CategoryID fromString(String value){
        return new CategoryID(value);
    }

    public static CategoryID from(final UUID id){
        return new CategoryID(id.toString().toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryID that = (CategoryID) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}