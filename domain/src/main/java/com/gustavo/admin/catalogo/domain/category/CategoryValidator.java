package com.gustavo.admin.catalogo.domain.category;

import com.gustavo.admin.catalogo.domain.validation.Error;
import com.gustavo.admin.catalogo.domain.validation.ValidationHandler;
import com.gustavo.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {

        private final Category category;

        public CategoryValidator(Category  category, final ValidationHandler handler) {
            super(handler);
            this.category = category;
        }

        @Override
        public void validate() {

            checkNameConstraints();

        }

    private void checkNameConstraints() {
            String name = category.getName();
        if (name == null ) {
            this.validatorHandler().append(new Error("'name' should not be null"));
            return;

        }
        if(name.isBlank()){
            this.validatorHandler().append(new Error("'name' should not be empty"));
        }
        final int length = name.trim().length();
        if (length < 3 || length > 255) {
            this.validatorHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }
}
