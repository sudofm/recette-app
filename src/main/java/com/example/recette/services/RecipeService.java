package com.example.recette.services;

import com.example.recette.command.RecipeCommand;
import com.example.recette.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface RecipeService{

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long id);
}
