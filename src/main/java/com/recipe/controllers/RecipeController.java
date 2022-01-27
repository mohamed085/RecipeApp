package com.recipe.controllers;

import com.recipe.domain.Recipe;
import com.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/show/{id}")
    public String getRecipe(@PathVariable String id, Model model) throws Exception {
        log.info("---Show recipe: " + id);

        model.addAttribute("recipe", recipeService.findById(new Long(id)));
        return "recipe/show";
    }
}
