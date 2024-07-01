package com.gustavo.admin.catalogo.application.category.create;

import com.gustavo.admin.catalogo.domain.category.Category;
import com.gustavo.admin.catalogo.domain.category.CategoryID;

public record CreateCategoryOutput(
        CategoryID id

) {

    public static CreateCategoryOutput from(Category category) {
        return new CreateCategoryOutput(category.getId());
    }
}
