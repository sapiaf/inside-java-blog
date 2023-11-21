package dev.sapia.blog.controller.admin;

import dev.sapia.blog.model.*;
import dev.sapia.blog.repository.*;
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
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String index(Model model) {
        List<Post> posts = postRepository.findAllByOrderByDateDesc();
        List<Comment> comments = commentRepository.findTop10ByOrderByDateTimeDesc();
        List<Message> messages = messageRepository.findTop10ByOrderByDateDesc();

        int totalViews = 0;
        for (Post post : posts) {
            Integer views = post.getViews();
            if (views != null) {
                totalViews += views;
            }
        }

        model.addAttribute("totalViews", totalViews);
        model.addAttribute("posts", posts);
        model.addAttribute("comments", comments);
        model.addAttribute("messages", messages);
        return "admin/admin";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, BindingResult bindingResult) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A user with username " + user.getEmail() + " already exists."
            );
        }

        if (bindingResult.hasErrors()) return "user/register";

        Optional<Role> defaultRole = roleRepository.findById(2);
        if (defaultRole.isPresent()) {
            user.setRole(defaultRole.get());
        }
        user.setRegistrationDate(LocalDate.now());
        user.setPassword("{noop}" + user.getPassword());
        userRepository.save(user);

        return "redirect:/login";
    }


}
