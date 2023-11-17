package dev.sapia.blog.controller.admin;

import dev.sapia.blog.model.Category;
import dev.sapia.blog.model.Post;
import dev.sapia.blog.model.User;
import dev.sapia.blog.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/posts/categories")
public class CategoryAdminController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "/admin/category/list";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/category/list";
        }
        categoryRepository.save(category);
        return "redirect:/admin/posts/categories";
    }

    @GetMapping("/update/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "admin/category/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category with id " + id + " not found");
        }
    }

    @PostMapping("/update/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("category") Category category,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/category/edit";
        }
        categoryRepository.save(category);
        return "redirect:/admin/posts/categories";
    }


    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        categoryRepository.deleteById(id);
        return "redirect:/admin/posts/categories";
    }
}
