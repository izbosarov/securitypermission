package com.example.securitypermission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.securitypermission.entity.Category;
import com.example.securitypermission.repository.CategoryRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    //    @PreAuthorize(value = "hasAnyAuthority('READ_CATEGORY')")
    @GetMapping
    public HttpEntity<?> getAll() {
        List<Category> all = categoryRepository.findAll();
        return ResponseEntity.ok(all);
    }

    //    @PreAuthorize(value = "hasAnyAuthority('ADD_CATEGORY')")
    @PostMapping
    public HttpEntity<?> save(@Valid @RequestBody Category category) {
        categoryRepository.save(new Category(category.getName()));

        return ResponseEntity.ok("Saved!");
    }

    //    @PreAuthorize(value = "hasAnyAuthority('DELETE_CATEGORY')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable int id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok("delete");
        } else {
            return ResponseEntity.ok("topilmadi");
        }

    }

    //    @PreAuthorize(value = "hasAnyAuthority('EDIT_CATEG0RY')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable int id, @RequestBody Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return ResponseEntity.ok("Topilmadi");
        Category category1 = optionalCategory.get();
        category1.setName(category.getName());
        categoryRepository.save(category1);
        return ResponseEntity.ok("Ozgartirildi");
    }

    @GetMapping("/search")
    public HttpEntity<?> search(@RequestParam String name) {
        List<Category> all = categoryRepository.findAllByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(all);
    }
}
