package com.recipe.services.servicesImp;

import com.recipe.domain.Recipe;
import com.recipe.repositories.RecipeRepository;
import com.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImp implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImp(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.info("---Get all recipes");

        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

        return recipes;
    }

    @Override
    public Recipe getRecipeById(Long id) throws Exception {
        log.info("---Get recipe by id: " + id);

        Optional<Recipe> recipe = recipeRepository.findById(id);

        if (!recipe.isPresent()) {
            throw new Exception("Recipe Not found");
        }

        return recipe.get();
    }
}
