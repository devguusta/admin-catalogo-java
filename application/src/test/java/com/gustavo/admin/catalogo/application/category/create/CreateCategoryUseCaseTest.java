package com.gustavo.admin.catalogo.application.category.create;

import com.gustavo.admin.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.List;
import java.util.Objects;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class CreateCategoryUseCaseTest {



    @Test
    public void giveAValidCommand_whenCallsCreateCategory_shouldReturnCategory(){
        //Arrange
        final var expectedName = "Filmes";
        final var expectedDescription = "Filmes de ação";
        final var expectedIsActive = true;

        final var gateway = Mockito.mock(CategoryGateway.class);

        when(gateway.create(any()))
                .thenAnswer(returnsFirstArg());
        CreateCategoryCommand createCategoryCommand =  CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);
        CreateCategoryUseCase createCategoryUseCase = new DefaultCreateCategoryUseCase(gateway);

        //Act
        createCategoryUseCase.execute(createCategoryCommand);

        //Assert
        Mockito.verify(gateway, times(1)).create(argThat(aCategory ->
                Objects.equals(expectedName, aCategory.getName())
                        && Objects.equals(expectedDescription, aCategory.getDescription())
                        && Objects.equals(expectedIsActive, aCategory.isActive())
                        && Objects.nonNull(aCategory.getId())
                        && Objects.nonNull(aCategory.getCreatedAt())
                        && Objects.nonNull(aCategory.getUpdatedAt())
                        && Objects.isNull(aCategory.getDeletedAt())
        ));
    }
}
