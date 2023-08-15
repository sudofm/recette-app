package com.example.recette.services;

import com.example.recette.domain.Recipe;
import com.example.recette.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private  final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    public void saveImageFile(Long id, MultipartFile file) {
        log.debug("Received a file");
        try {
            Recipe recipe = recipeRepository.findById(id).get();
            Byte[] bytesObjects = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b: file.getBytes()) {
                bytesObjects[i++] = b;
            }
            recipe.setImages(bytesObjects);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("Error occured", e);
            e.printStackTrace();
        }
    }
}
