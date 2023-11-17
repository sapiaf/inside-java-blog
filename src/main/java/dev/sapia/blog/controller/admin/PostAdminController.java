package dev.sapia.blog.controller.admin;

import dev.sapia.blog.model.Category;
import dev.sapia.blog.model.Post;
import dev.sapia.blog.model.User;
import dev.sapia.blog.repository.CategoryRepository;
import dev.sapia.blog.repository.PostRepository;
import dev.sapia.blog.repository.UserRepository;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/posts")
public class PostAdminController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String index(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "admin/post/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "admin/post/create";
    }
    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("post") Post post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/post/create";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName();
        Optional<User> loggedInUserOptional = userRepository.findByEmail(loggedInUserEmail);
        if (loggedInUserOptional.isPresent()) {
            User loggedInUser = loggedInUserOptional.get();
            post.setAuthor(loggedInUser);
            post.setDate(LocalDate.now());
            postRepository.save(post);
            return "redirect:/admin/posts";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/update/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/post/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "post with id " + id + " not found");
        }
    }

    @PostMapping("/update/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("post") Post post,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/post/edit";
        }
        post.setDate(LocalDate.now());
        postRepository.save(post);
        return "redirect:/admin/posts";
    }


    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        postRepository.deleteById(id);
        return "redirect:/admin/posts";
    }
}
