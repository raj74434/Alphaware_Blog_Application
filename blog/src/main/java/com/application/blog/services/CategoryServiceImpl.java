package com.application.blog.services;

import com.application.blog.models.Category;
import com.application.blog.models.Users;
import com.application.blog.repository.CategoryRepo;
import com.application.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements  CategoryService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category createNewCategory(Integer userId, Category category) throws Exception {
        Optional<Users> categoryTem = userRepo.findById(userId);
        if(categoryTem.isPresent()) {
            return categoryRepo.save(category);
        }else {
            throw new Exception("Category did not exist with id"+userId);
        }
    }

    @Override
    public Category deleteCategory(Integer categoryId) throws Exception {
        Optional<Category> categoryTem = categoryRepo.findById(categoryId);
        if(categoryTem.isPresent()) {
            Category category = categoryTem.get();
            categoryRepo.deleteById(categoryId);
            return category;
        }
        else {
            throw new Exception("Category did not exist with id"+categoryId);
        }
    }

    @Override
    public Category getCategory(Integer categoryId) throws Exception {
        Optional<Category> categoryTem = categoryRepo.findById(categoryId);
        if(categoryTem.isPresent()) {
            return categoryTem.get();
        }
        else {
            throw new Exception("Category did not exist with id"+categoryId);
        }
    }

    @Override
    public Category updateCategory(Integer categoryId, Category category) throws Exception {
        Optional<Category> categoryTem = categoryRepo.findById(categoryId);
        if(categoryTem.isPresent()) {
            Category existingCat = categoryTem.get();
            existingCat.setCategoryName(category.getCategoryName());
            return categoryRepo.save(existingCat);
        }
        else {
            throw new Exception("Category did not exist with id"+categoryId);
        }
    }

    @Override
    public List<Category> getAllCategory() throws Exception {
        List<Category> catList = categoryRepo.findAll();
        if(!catList.isEmpty()) {
            return catList;
        }
        else {
            throw new Exception("Any Category did not exist ");
        }
    }
}
