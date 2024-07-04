package com.gustavo.admin.catalogo.application.category.create;

import com.gustavo.admin.catalogo.application.UseCase;
import com.gustavo.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase  extends UseCase<CreateCategoryCommand, Either<Notification,CreateCategoryOutput>> {
}
