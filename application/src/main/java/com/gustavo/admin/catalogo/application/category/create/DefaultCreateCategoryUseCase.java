package com.gustavo.admin.catalogo.application.category.create;

import com.gustavo.admin.catalogo.domain.category.Category;
import com.gustavo.admin.catalogo.domain.category.CategoryGateway;
import com.gustavo.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateCategoryUseCase  extends  CreateCategoryUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CreateCategoryOutput execute( final CreateCategoryCommand input) {
       final var name = input.name();
         final var description = input.description();
            final var isActive = input.isActive();
            final var category = Category.newCategory(name, description, isActive);
            category.validate(new ThrowsValidationHandler());

        return CreateCategoryOutput.from(categoryGateway.create(category));
    }
}
