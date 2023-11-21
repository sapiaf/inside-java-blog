package dev.sapia.blog.controller.user;

import dev.sapia.blog.model.Message;
import dev.sapia.blog.model.Post;
import dev.sapia.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private PostRepository postRepository;
    @GetMapping
    public String index(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "index";
    }
    @GetMapping("/about")
    public String about() {
        return "user/about";
    }

}
