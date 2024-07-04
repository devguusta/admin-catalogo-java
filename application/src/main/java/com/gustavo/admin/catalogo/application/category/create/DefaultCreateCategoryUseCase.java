package com.gustavo.admin.catalogo.application.category.create;

import com.gustavo.admin.catalogo.application.UseCase;
import com.gustavo.admin.catalogo.domain.category.Category;
import com.gustavo.admin.catalogo.domain.category.CategoryGateway;
import com.gustavo.admin.catalogo.domain.validation.handler.Notification;
import com.gustavo.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand input) {
        final var name = input.name();
        final var description = input.description();
        final var isActive = input.isActive();

        final var notification = Notification.create();
        final var category = Category.newCategory(name, description, isActive);
        category.validate(notification);


        return notification.hasError() ? API.Left(notification) : create(category);
    }

    private Either<Notification, CreateCategoryOutput> create(Category input) {
        return API.Try(() -> this.categoryGateway.create(input)).toEither().bimap(Notification::create, CreateCategoryOutput::from);

    }
}
