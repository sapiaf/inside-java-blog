package dev.sapia.blog.controller.admin;

import dev.sapia.blog.model.Comment;
import dev.sapia.blog.model.User;
import dev.sapia.blog.repository.CommentRepository;
import dev.sapia.blog.repository.PostRepository;
import dev.sapia.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/comments")
public class CommentAdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public String index(Model model) {
        List<Comment> comments = commentRepository.findAll();
        model.addAttribute("comments", comments);
        return "/admin/comment/list";
    }
}
