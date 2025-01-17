package com.gustavo.admin.catalogo.domain.category;

import com.gustavo.admin.catalogo.domain.category.Category;
import com.gustavo.admin.catalogo.domain.exceptions.DomainException;
import com.gustavo.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    @Test
    public void giveAValidParams_whenCallNewCategory_thenInstatiateACategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void giveAnInvalidNullName_whenCallNewCategory_thenShouldReturnException() {
        final String expectedName = null;
        final String expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());


    }

    @Test
    public void giveAnEmptyName_whenCallNewCategory_thenShouldReturnException() {
        final String expectedName = " ";
        final String expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());


    }

    @Test
    public void giveAnInvalidNameLengthLessThan3_whenCallNewCategory_thenShouldReturnException() {
        final String expectedName = "Fi ";
        final String expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());


    }

    @Test
    public void giveAnInvalidNameLengthGreaterThan255_whenCallNewCategory_thenShouldReturnException() {
        final String expectedName = """
                Lorem ipsum dolor sit amet. Ut totam alias ex alias consequatur est labore temporibus vel veniam dolore. Qui velit accusantium id eligendi beatae qui quidem natus a ipsa ratione id porro corporis non iusto corrupti ut omnis ipsam. At delectus voluptatem qui culpa ipsum est quod consequatur.
                                
                Ut dolore quia aut Quis odio in voluptatem debitis! Et necessitatibus dolorem ea molestiae ipsa qui dolor aliquid aut dolores voluptatem est magni tenetur. Qui nobis fugiat et aliquid tenetur ad tempore similique sit omnis laborum et quia eligendi sed pariatur ipsum. Aut dolorem autem qui porro unde sit architecto itaque ex iste iusto et molestiae quam.
                                
                Eos omnis officiis in esse possimus qui minus beatae sit similique cupiditate. Aut ipsum adipisci vel voluptatem dolor qui assumenda consectetur et cupiditate debitis et mollitia consequuntur ut rerum pariatur.
                """;
        final String expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());


    }

    @Test
    public void giveAValidEmptyDescription_whenCallNewCategory_thenReturnOk() {
        final var expectedName = "Filmes";
        final var expectedDescription = "";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());


    }

    @Test
    public void giveAValidFalseActive_whenCallNewCategory_thenReturnOk() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    public void giveAValidActiveCategory_whenCallDeactivate_thenReturnCategoryInactive(){

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var updatedAt = category.getUpdatedAt();
        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));



        Assertions.assertNull(category.getDeletedAt());
        Assertions.assertTrue(category.isActive());

        final var actualCategory =category.deactivate();

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(category.getId(),actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertFalse(actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getDeletedAt());

    }
    @Test
    public void giveAValidInactiveCategory_whenCallActivate_thenReturnCategoryActive(){

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;
        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var updatedAt = category.getUpdatedAt();

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));



        Assertions.assertNotNull(category.getDeletedAt());
        Assertions.assertFalse(category.isActive());

        final var actualCategory =category.activate();

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(category.getId(),actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertTrue(actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());

    }
    @Test
    public void giveAValidCategory_whenCallUpdate_thenReturnCategoryUpdated(){

            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var expectedIsActive = true;
            final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
            final var updatedAt = category.getUpdatedAt();
            final var expectedNewName = "Séries";
            final var expectedNewDescription = "A categoria mais assistida do Brasil";


            final var actualCategory = category.update(expectedNewName, expectedNewDescription, expectedIsActive);

            Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

            Assertions.assertEquals(category.getId(),actualCategory.getId());
            Assertions.assertEquals(expectedNewName, actualCategory.getName());
            Assertions.assertEquals(expectedNewDescription, actualCategory.getDescription());
            Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
            Assertions.assertNotNull(actualCategory.getCreatedAt());

            Assertions.assertNull(actualCategory.getDeletedAt());
    }
    @Test
    public void giveAValidCategoryInactive_whenCallUpdateWithActive_thenReturnActivateCategoryUpdated(){

            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var expectedIsActive = true;
            final var category = Category.newCategory(expectedName, expectedDescription, false);
            final var updatedAt = category.getUpdatedAt();
            final var expectedNewName = "Séries";
            final var expectedNewDescription = "A categoria mais assistida do Brasil";


            final var actualCategory = category.update(expectedNewName, expectedNewDescription, expectedIsActive);

            Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

            Assertions.assertEquals(category.getId(),actualCategory.getId());
            Assertions.assertEquals(expectedNewName, actualCategory.getName());
            Assertions.assertEquals(expectedNewDescription, actualCategory.getDescription());
            Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
            Assertions.assertNotNull(actualCategory.getCreatedAt());

            Assertions.assertNull(actualCategory.getDeletedAt());
    } @Test
    public void giveAValidCategoryInactive_whenCallUpdateWithInvalidParams_thenThrowException(){

            final String expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var expectedIsActive = true;
            final var category = Category.newCategory(expectedName, expectedDescription, false);
            final var updatedAt = category.getUpdatedAt();
            final String expectedNewName = null;
            final var expectedNewDescription = "A categoria mais assistida do Brasil";


            final var actualCategory = category.update(expectedNewName, expectedNewDescription, expectedIsActive);



            Assertions.assertEquals(category.getId(),actualCategory.getId());
            Assertions.assertEquals(expectedNewName, actualCategory.getName());
            Assertions.assertEquals(expectedNewDescription, actualCategory.getDescription());
            Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
            Assertions.assertNotNull(actualCategory.getCreatedAt());

            Assertions.assertNull(actualCategory.getDeletedAt());
    }
}
