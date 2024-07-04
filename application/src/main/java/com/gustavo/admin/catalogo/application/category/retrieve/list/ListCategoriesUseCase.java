package com.gustavo.admin.catalogo.application.category.retrieve.list;

import com.gustavo.admin.catalogo.application.UseCase;
import com.gustavo.admin.catalogo.domain.pagination.SearchQuery;
import com.gustavo.admin.catalogo.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
        extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}