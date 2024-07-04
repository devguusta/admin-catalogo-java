package com.gustavo.admin.catalogo.application.category.update;

import com.gustavo.admin.catalogo.application.UseCase;
import com.gustavo.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase
        extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}