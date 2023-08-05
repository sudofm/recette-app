package com.example.recette.controllers;

import com.example.recette.command.RecipeCommand;
import com.example.recette.domain.Recipe;
import com.example.recette.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static java.lang.Long.parseLong;

@Slf4j
@Controller
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {

        model.addAttribute("recipe", recipeService.findById(parseLong(id)));
        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String createRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeForm";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(parseLong(id)));
        return "recipe/recipeForm";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/"+ savedCommand.getId() +"/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id, Model model) {
        log.debug("Deleting id :" + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
