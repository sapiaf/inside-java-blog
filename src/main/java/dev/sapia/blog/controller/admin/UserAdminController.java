package dev.sapia.blog.controller.admin;


import dev.sapia.blog.model.Role;
import dev.sapia.blog.model.User;
import dev.sapia.blog.repository.PostRepository;
import dev.sapia.blog.repository.RoleRepository;
import dev.sapia.blog.repository.UserRepository;
import dev.sapia.blog.security.DatabaseUserDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String index(Model model) {
        List<User> users = userRepository.findAll();
        List<Integer> postCounts = new ArrayList<>();
        for (User user : users) {
            Integer userCount = postRepository.countByAuthorId(user.getId());
            postCounts.add(userCount);
        }
        model.addAttribute("users", users);
        model.addAttribute("postCounts", postCounts);
        return "/admin/user/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<User> users = userRepository.findAll();
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "admin/user/create";
    }
    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/admin/user/create";
        }

        user.setRegistrationDate(LocalDate.now());
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        DatabaseUserDetails loggedUserDetails = (DatabaseUserDetails) authentication.getPrincipal();
        Integer userId = loggedUserDetails.getId();
        User loggedUser = userRepository.findById(userId).get();
        List<Role> roles = roleRepository.findAll();

        model.addAttribute("roles", roles);
        model.addAttribute("user", loggedUser);
        return "admin/user/detail";
    }

    @GetMapping("/profile/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<User> user = userRepository.findById(id);
        List<Role> roles = roleRepository.findAll();
        if (user.isPresent()) {
            model.addAttribute("roles", roles);
            model.addAttribute("user", user.get());
            return "admin/user/profile";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user with id " + id + " not found");
        }
    }

    @PostMapping("/profile/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            } else {
                String encodedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(encodedPassword);
            }

            user.setRegistrationDate(existingUser.getRegistrationDate());
            userRepository.save(user);
            return "redirect:/admin/users";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user with id " + id + " not found");
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }
}
