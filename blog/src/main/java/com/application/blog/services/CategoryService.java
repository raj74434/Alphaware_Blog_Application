package com.application.blog.services;

import com.application.blog.models.Category;

import java.util.List;

public interface CategoryService {
    public Category createNewCategory(Integer userId, Category category) throws Exception;
    public Category deleteCategory(Integer categoryId) throws Exception;

    public Category getCategory(Integer categoryId) throws Exception ;
    public Category updateCategory(Integer categoryId, Category category) throws Exception;

    public List<Category> getAllCategory() throws Exception;

}
