package com.shengyu.tacos.data;

import com.shengyu.tacos.Ingredient;
public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);
}
