package com.gustavo.admin.catalogo.domain.category;

import com.gustavo.admin.catalogo.domain.validation.Error;
import com.gustavo.admin.catalogo.domain.validation.ValidationHandler;
import com.gustavo.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 255;
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
        if (length <
                NAME_MIN_LENGTH || length > NAME_MAX_LENGTH) {
            this.validatorHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }
}
