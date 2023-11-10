package dev.sapia.blog.controller.admin;

import dev.sapia.blog.model.Post;
import dev.sapia.blog.repository.PostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public String index(Model model) {
        return "admin/admin";
    }

    @GetMapping("/post/create")
    public String create(Model model) {
        return "post/create";
    }
    @PostMapping("/post/create")
    public String doCreate(@Valid @ModelAttribute("post") Post post) {
        postRepository.save(post);
        return "redirect:/";
    }
}
