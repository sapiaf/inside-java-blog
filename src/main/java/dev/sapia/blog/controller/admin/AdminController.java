package dev.sapia.blog.controller.admin;

import dev.sapia.blog.model.Category;
import dev.sapia.blog.model.Post;
import dev.sapia.blog.model.Role;
import dev.sapia.blog.model.User;
import dev.sapia.blog.repository.CategoryRepository;
import dev.sapia.blog.repository.PostRepository;
import dev.sapia.blog.repository.RoleRepository;
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
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String index(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
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
