package com.gustavo.admin.catalogo.application;



public abstract class UseCase<IN,OUT> {

    public abstract OUT execute(IN input);
}