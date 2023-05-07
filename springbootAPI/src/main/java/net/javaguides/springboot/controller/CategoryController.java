package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Category;
import net.javaguides.springboot.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

    // create category REST API
    @PostMapping()
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        return new ResponseEntity<Category>(categoryService.saveCategory(category), HttpStatus.CREATED);
    }

    // get all categories REST API
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // get category by id REST API
    // http://localhost:8080/api/categories/1
    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int categoryId) {
        return new ResponseEntity<Category>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

    // update category REST API
    // http://localhost:8080/api/categories/1
    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
        return new ResponseEntity<Category>(categoryService.updateCategory(category, id), HttpStatus.OK);
    }

    // delete category REST API
    // http://localhost:8080/api/categories/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id) {

        // delete category from DB
        categoryService.deleteCategory(id);

        return new ResponseEntity<String>("Category deleted successfully!.", HttpStatus.OK);
    }
}

