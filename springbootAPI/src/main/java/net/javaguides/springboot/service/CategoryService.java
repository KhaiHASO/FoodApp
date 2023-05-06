package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(long id);
    Category updateCategory(Category category, long id);
    void deleteCategory(long id);
}

