package com.gustavo.admin.catalogo.domain.entities;

import com.gustavo.admin.catalogo.domain.valueobject.Identifier;

public abstract  class AggregateRoot<ID extends Identifier> extends  Entity<ID> {

    protected  AggregateRoot(final ID  id){
        super(id);
    }
}
