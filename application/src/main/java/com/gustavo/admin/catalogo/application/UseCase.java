package com.gustavo.admin.catalogo.application;

import com.gustavo.admin.catalogo.domain.category.Category;

public class UseCase {
    public Category execute() {
        return new Category();
    }
}