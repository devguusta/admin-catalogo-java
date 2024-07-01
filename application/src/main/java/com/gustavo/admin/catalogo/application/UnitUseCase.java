package com.gustavo.admin.catalogo.application;

public abstract class UnitUseCase<IN> {

    public abstract void execute(IN input);
}
