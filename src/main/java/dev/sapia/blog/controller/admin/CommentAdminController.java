package dev.sapia.blog.controller.admin;

import dev.sapia.blog.model.Comment;
import dev.sapia.blog.model.Message;
import dev.sapia.blog.model.Post;
import dev.sapia.blog.model.User;
import dev.sapia.blog.repository.CommentRepository;
import dev.sapia.blog.repository.PostRepository;
import dev.sapia.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public String index(@RequestParam(value = "query", required = false) String searchKeyword, Model model) {
        List<Comment> comments;
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            comments = commentRepository.findByContentContainingIgnoreCase(searchKeyword);
        } else {
            comments = commentRepository.findAll();
        }

        model.addAttribute("comments", comments);
        return "admin/comment/list";
    }
    @GetMapping("/{commentId}")
    public String show(@PathVariable("commentId") Integer id, Model model) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            model.addAttribute("comment", comment);
            return "admin/comment/detail";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        commentRepository.deleteById(id);
        return "redirect:/admin/comments";
    }
}
