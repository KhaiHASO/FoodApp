package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(int id);
    Category updateCategory(Category category, int id);
    void deleteCategory(int id);
}

