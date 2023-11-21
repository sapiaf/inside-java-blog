package dev.sapia.blog.controller.user;

import dev.sapia.blog.model.Comment;
import dev.sapia.blog.model.Post;
import dev.sapia.blog.model.User;
import dev.sapia.blog.repository.CommentRepository;
import dev.sapia.blog.repository.PostRepository;
import dev.sapia.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/{blogId}")
    public String show(@PathVariable("blogId") Integer id, Model model) {
        Optional<Post> postOptional = postRepository.findById(id);
        List<Comment> comments = commentRepository.findByPost_Id(id);
        if (postOptional.isPresent()) {
            Post postFromDB = postOptional.get();
            postFromDB.incrementViews();
            postRepository.save(postFromDB);
            model.addAttribute("post", postFromDB);
            model.addAttribute("comments", comments);
            return "user/detail";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{blogId}")
    public String saveComment(@RequestParam("commentText") String commentText,
                              @RequestParam("postId") Integer postId,
                              Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Optional<User> currentUser = userRepository.findByEmail(currentUserName);

        Optional<Post> postOptional = postRepository.findById(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();

            Comment comment = new Comment();
            comment.setContent(commentText);
            comment.setDateTime(LocalDateTime.now());
            comment.setUser(currentUser.get());

            comment.setPost(post);

            commentRepository.save(comment);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/posts/" + postId;
    }
}
