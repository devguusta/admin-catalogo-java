package com.gustavo.admin.catalogo.domain.pagination;
import java.util.List;
public record Pagination<T>(
        int currentPage,
        int perPage,

        long totalElements,
        List<T> items
) {
}
