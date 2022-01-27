package com.recipe.services;

import com.recipe.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe getRecipeById(Long id) throws Exception;
}
