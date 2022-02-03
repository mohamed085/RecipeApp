package com.recipe.controllers;

import com.recipe.commands.RecipeCommand;
import com.recipe.domain.Recipe;
import com.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public String getRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(new Long(id)));
        return "recipe/show";
    }

    @GetMapping("")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());

        return "recipe/recipeform";
    }

    @PostMapping("")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);


        return "redirect:/recipe/" + savedCommand.getId();
    }

}
