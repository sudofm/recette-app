package com.example.recette.controllers;

import com.example.recette.services.IngredientService;
import com.example.recette.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {

    private RecipeService recipeService;
    private IngredientService ingredientService;
    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {

        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        log.debug("Getting ingredient list for recipeId :" + recipeId);

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        log.debug("showing ingredient :" + ingredientId);

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),
                Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }
}
