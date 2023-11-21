package dev.sapia.blog.api;

import dev.sapia.blog.model.Category;
import dev.sapia.blog.repository.CategoryRepository;
import dev.sapia.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
public class CategoryRestController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/postCountPerCategory")
    public ResponseEntity<?> getPostCountPerCategory() {
        List<Category> categories = categoryRepository.findAll();
        Map<String, Long> postCountPerCategory = new HashMap<>();

        for (Category category : categories) {
            Long count = (long) postRepository.countPostsByCategory(category.getId());
            postCountPerCategory.put(category.getName(), count);
        }

        return ResponseEntity.ok(postCountPerCategory);
    }


}
