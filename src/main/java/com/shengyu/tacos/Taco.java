package com.shengyu.tacos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Data
public class Taco {

    // end::allButValidation[]
    private Long id;
    private Date createdAt;
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    // tag::allButValidation[]
    private String name;

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    // end::allButValidation[]
    @Size(min=1, message="You must choose at least 1 ingredient")
    // tag::allButValidation[]
    private List<Ingredient> ingredients;

}