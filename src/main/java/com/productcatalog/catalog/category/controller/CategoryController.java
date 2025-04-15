package com.productcatalog.catalog.category.controller;

import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productcatalog.catalog.category.model.Category;
import com.productcatalog.catalog.category.service.CategoryService;
import com.productcatalog.exception.CustomException;

@RestController
@RequestMapping(path = "categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	
	@GetMapping(produces = "application/json")
	public Page<Category> getListOfAllCategories(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
		return categoryService.getAllCategories(PageRequest.of(page, size));
	}

	@GetMapping(path = "/{categoryId}", produces = "application/json")
	public Category getCategoryById(@PathVariable Long categoryId) {
		try {
			return categoryService.getCategoryById(categoryId);
		} catch (CustomException e) {
			return null;
		}
	}


	@PostMapping(consumes = "application/json", produces = "application/json")
	public Status addCategories(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}

	
	@DeleteMapping(path = "/{categoryId}", produces = "application/json")
	public Status deleteCategory(@PathVariable Long categoryId) {
		return categoryService.deleteCategory(categoryId);
	}

	
	@PutMapping(path = "/{categoryId}", produces = "application/json")
	public Status updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
		return categoryService.updateCategory(categoryId, category);
	}

	// getter setter
	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setProductService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
}
