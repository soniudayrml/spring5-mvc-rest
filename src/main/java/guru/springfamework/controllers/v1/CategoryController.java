package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CategoryListDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {
    private final CategoryService categoryService;
    public final static String BASE_URL="/api/v1/categories";


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories()
    {
//        return new ResponseEntity<CategoryListDTO>(new CategoryListDTO(categoryService.getAllCategories()),
//                HttpStatus.OK);
        return new CategoryListDTO(categoryService.getAllCategories());
//        return new ResponseEntity<CategoryListDTO>(new CategoryListDTO(categoryService.getAllCategories()),
//                HttpStatus.OK);
    }
    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name)
    {
        return categoryService.getCategoryByName(name);
//        return new ResponseEntity<CategoryDTO>(categoryService.getCategoryByName(name),HttpStatus.OK);
//        return new ResponseEntity<Category>(categoryService.getCategoryByName(name),HttpStatus.OK);
    }
}
