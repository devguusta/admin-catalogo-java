package com.gustavo.admin.catalogo.application.category.create;

import com.gustavo.admin.catalogo.domain.category.CategoryGateway;
import com.gustavo.admin.catalogo.domain.validation.handler.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {
    @InjectMocks
    private DefaultCreateCategoryUseCase useCase;


    @Mock
    private CategoryGateway gateway;


    @Test
    public void giveAValidCommand_whenCallsCreateCategory_shouldReturnCategory() {
        //Arrange
        final var expectedName = "Filmes";
        final var expectedDescription = "Filmes de ação";
        final var expectedIsActive = true;


        when(gateway.create(any()))
                .thenAnswer(returnsFirstArg());
        CreateCategoryCommand createCategoryCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);


        //Act
       final var output = useCase.execute(createCategoryCommand).get();



        //Assert

        Assertions.assertNotNull(output);

        Assertions.assertNotNull(output.id());



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

    @Test
    public void giveAInvalidName_whenCallsCreateCategory_thenShouldReturnDomainException() {
        //Arrange
        final String expectedName = null;
        final var expectedDescription = "Filmes de ação";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' should not be null";

        final var expectedErrorCount = 1;


        CreateCategoryCommand createCategoryCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

      final  Notification notification = useCase.execute(createCategoryCommand).getLeft();


        //Assert
        Assertions.assertNotNull(notification);
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());





        Mockito.verify(gateway, times(0)).create(any());


    }

    @Test
    public void giveAValidCommandWithInactiveCategory_whenCallsCreateCategory_shouldReturnCategory() {
        //Arrange
        final var expectedName = "Filmes";
        final var expectedDescription = "Filmes de ação";
        final var expectedIsActive = false;


        when(gateway.create(any()))
                .thenAnswer(returnsFirstArg());
        CreateCategoryCommand createCategoryCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);


        //Act
        CreateCategoryOutput categoryOutput = useCase.execute(createCategoryCommand).get();



        //Assert

        Assertions.assertNotNull(categoryOutput);
        Assertions.assertNotNull(categoryOutput.id());

        Mockito.verify(gateway, times(1)).create(argThat(aCategory ->
                Objects.equals(expectedName, aCategory.getName())
                        && Objects.equals(expectedDescription, aCategory.getDescription())
                        && Objects.equals(expectedIsActive, aCategory.isActive())
                        && Objects.nonNull(aCategory.getId())
                        && Objects.nonNull(aCategory.getCreatedAt())
                        && Objects.nonNull(aCategory.getUpdatedAt())
                        && Objects.nonNull(aCategory.getDeletedAt())
        ));
    }

    @Test
    public void giveAValidCommand_whenGatewayThrowsRandomException_shouldReturnException() {
        //Arrange
        final var expectedName = "Filmes";
        final var expectedDescription = "Filmes de ação";
        final var expectedIsActive = true;

        final var expectedErrorMessage = "Random exception";

        final var expectedErrorCount = 1;


        CreateCategoryCommand createCategoryCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);


        when(gateway.create(any()))
                .thenThrow(new RuntimeException("Random exception"));

        final  Notification notification = useCase.execute(createCategoryCommand).getLeft();


        //Assert
        Assertions.assertNotNull(notification);
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());







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
