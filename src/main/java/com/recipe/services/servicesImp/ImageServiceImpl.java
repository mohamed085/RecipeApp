package com.recipe.services.servicesImp;

import com.recipe.domain.Recipe;
import com.recipe.repositories.RecipeRepository;
import com.recipe.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        try {
            Recipe recipe = recipeRepository.findById(Long.valueOf(recipeId)).get();

            Byte[] bytes = new Byte[file.getBytes().length];

            int i = 0;
            for (byte b : file.getBytes()) {
                bytes[i++] = b;
            }

            recipe.setImage(bytes);
            recipeRepository.save(recipe);

        } catch (IOException exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
        }


    }
}
