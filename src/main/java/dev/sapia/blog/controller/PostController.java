package dev.sapia.blog.controller;

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

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/create")
    public String create(Model model) {
        return "post/create";
    }
    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("post") Post post) {
        postRepository.save(post);
        return "redirect:/";
    }
}
