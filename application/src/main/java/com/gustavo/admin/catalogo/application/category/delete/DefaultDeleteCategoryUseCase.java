package com.gustavo.admin.catalogo.application.category.delete;

import com.gustavo.admin.catalogo.domain.category.CategoryGateway;
import com.gustavo.admin.catalogo.domain.category.CategoryID;

import java.util.Objects;

public class DefaultDeleteCategoryUseCase extends DeleteCategoryUseCase{
    private final CategoryGateway categoryGateway;

    public DefaultDeleteCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public void execute(String input) {
        this.categoryGateway.deleteById(CategoryID.from(input));

    }
}
