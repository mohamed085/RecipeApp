package com.recipe.controllers;

import com.recipe.commands.RecipeCommand;
import com.recipe.domain.Recipe;
import com.recipe.exceptions.NotFoundException;
import com.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        log.debug("Getting recipe: " + id);

        model.addAttribute("recipe", recipeService.findById(new Long(id)));
        return "recipe/show";
    }

    @GetMapping("")
    public String newRecipe(Model model) {
        log.debug("Getting new recipe form");

        model.addAttribute("recipe", new Recipe());

        return "recipe/recipeform";
    }

    @PostMapping("")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
        log.debug("Save or update new recipe: " + recipeCommand.getDescription());

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);


        return "redirect:/recipe/" + savedCommand.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        log.debug("Deleting recipe id: " + id);

        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable String id, Model model) {
        log.debug("Getting update recipe id: " + id);

        Recipe recipe = recipeService.findById(Long.valueOf(id));
        model.addAttribute("recipe", recipe);

        return "recipe/recipeform";
    }


}
