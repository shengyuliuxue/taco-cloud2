package com.shengyu.tacos.data;

import com.shengyu.tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository extends CrudRepository<Ingredient, String>{

}