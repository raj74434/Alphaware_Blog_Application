package com.application.blog.controller;

import com.application.blog.models.Category;
import com.application.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(" /api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/{userId}")
    public ResponseEntity<Category> createNewCategory(@PathVariable Integer userId, @RequestBody Category category) throws Exception{
        return new ResponseEntity<>(categoryService.createNewCategory(userId, category), HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Category> deleteCategory(Integer categoryId) throws Exception{
        return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.ACCEPTED);
    }

    @GetMapping("{/{categoryId}}")
    public ResponseEntity<Category> getCategory(Integer categoryId) throws Exception {
        return new ResponseEntity<>(categoryService.getCategory(categoryId), HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(Integer categoryId, @RequestBody Category category) throws Exception{
        return new ResponseEntity<>(categoryService.updateCategory(categoryId,category), HttpStatus.ACCEPTED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategory() throws Exception{
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.ACCEPTED);
    }
}
