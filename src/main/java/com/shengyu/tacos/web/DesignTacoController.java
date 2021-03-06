package com.shengyu.tacos.web;

import com.shengyu.tacos.Ingredient;
import com.shengyu.tacos.Ingredient.Type;
import com.shengyu.tacos.Order;
import com.shengyu.tacos.Taco;
import com.shengyu.tacos.data.IngredientRepository;
import com.shengyu.tacos.data.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private TacoRepository designRepo;

    @ModelAttribute(name="order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name="taco")
    public Taco taco(){
        return new Taco();
    }

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository designRepo){
        this.ingredientRepository = ingredientRepository;
        this.designRepo = designRepo;
    }


    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));
        Type[] types = Ingredient.Type.values();
        for (Type type: types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        return "design";
    }

    @PostMapping
    public String processDesign(
            @Valid @ModelAttribute Taco design, Errors errors, @ModelAttribute Order order){
        if(errors.hasErrors()){
            return "design";
        }
        Taco  saved = designRepo.save(design);
        order.addDesign(saved);
        return "redirect:/orders/current";
    }




    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
