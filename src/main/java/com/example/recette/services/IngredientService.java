package com.example.recette.services;

import com.example.recette.command.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long IngredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
}
