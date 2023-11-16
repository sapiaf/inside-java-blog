package dev.sapia.blog.controller.user;

import dev.sapia.blog.model.Category;
import dev.sapia.blog.model.Post;
import dev.sapia.blog.repository.CategoryRepository;
import dev.sapia.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @GetMapping
    public String index(Model model) {
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categories", categoryList);
        return "user/categories";
    }
    @GetMapping("/{categoryId}")
    public String show(@PathVariable("categoryId") Integer id, Model model) {
        List<Post> postByCategory = postRepository.findByCategory_Id(id);

        model.addAttribute("posts", postByCategory);

        return "user/catalog";
    }
}
