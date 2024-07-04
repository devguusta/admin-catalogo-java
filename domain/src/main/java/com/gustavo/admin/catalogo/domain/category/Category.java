package com.gustavo.admin.catalogo.domain.category;

import com.gustavo.admin.catalogo.domain.entities.AggregateRoot;
import com.gustavo.admin.catalogo.domain.validation.ValidationHandler;

import java.time.Instant;


public class Category  extends AggregateRoot<CategoryID>  implements Cloneable {

   private  String name;

   private    String description;
   private  boolean active;

   private final Instant createdAt;

   private  Instant updatedAt;

   private  Instant deletedAt;

   private Category(
           final CategoryID id,
           final   String name,
           final    String description,
           final     boolean active,
           final     Instant createdAt,
           final      Instant updatedAt,
           final     Instant deletedAt) {
     super(id);
      this.name = name;
      this.description = description;
      this.active = active;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
      this.deletedAt = deletedAt;
   }

   public static Category newCategory(

                                        final   String name,
                                        final    String description,
                                        final     boolean active){

      final var id = CategoryID.unique();
      final Instant now = Instant.now();
      final var deletedAt =  active ? null : now;
      return new Category(id, name, description, active, now, now, deletedAt);

   }
   public static Category with(
           final CategoryID anId,
           final String name,
           final String description,
           final boolean active,
           final Instant createdAt,
           final Instant updatedAt,
           final Instant deletedAt
   ) {
      return new Category(
              anId,
              name,
              description,
              active,
              createdAt,
              updatedAt,
              deletedAt
      );
   }

   public static Category with(final Category aCategory) {
      return with(
              aCategory.getId(),
              aCategory.name,
              aCategory.description,
              aCategory.isActive(),
              aCategory.createdAt,
              aCategory.updatedAt,
              aCategory.deletedAt
      );
   }


   public Category deactivate(){
      if(getDeletedAt() == null){
         this.deletedAt = Instant.now();
      }
      this.active = false;
      this.updatedAt = Instant.now();
        return this;
   }

   public Category activate(){
      this.active = true;
      this.deletedAt = null;
      this.updatedAt = Instant.now();
        return this;

   }

   @Override
   public void validate(ValidationHandler handler) {
      new CategoryValidator(this, handler).validate();
   }
   public Category update(final String name, final String description, final boolean active){

      if(active){
         activate();
      } else {
            deactivate();
      }
      this.name = name;
      this.description = description;

      this.updatedAt = Instant.now();
      return this;
   }


   public CategoryID getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public boolean isActive() {
      return active;
   }

   public Instant getCreatedAt() {
      return createdAt;
   }

   public Instant getUpdatedAt() {
      return updatedAt;
   }

   public Instant getDeletedAt() {
      return deletedAt;
   }


   @Override
   public Category clone() {
      try {
         return (Category) super.clone();
      } catch (CloneNotSupportedException e) {
         throw new AssertionError();
      }
   }


}