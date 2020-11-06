package com.shengyu.tacos.data;

import com.shengyu.tacos.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToIngredient implements Converter<String, Ingredient> {
    @Autowired
    private  IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String s) {
        //my work add data from database
        return ingredientRepository.findOne(s);
    }
}
