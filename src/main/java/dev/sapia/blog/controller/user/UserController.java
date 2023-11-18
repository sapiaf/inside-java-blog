package dev.sapia.blog.controller.user;

import dev.sapia.blog.model.Category;
import dev.sapia.blog.model.Post;
import dev.sapia.blog.model.User;
import dev.sapia.blog.repository.CategoryRepository;
import dev.sapia.blog.repository.PostRepository;
import dev.sapia.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String index(Model model) {
        List<User> categoryList = userRepository.findAll();
        model.addAttribute("users", categoryList);
        return "user/users";
    }
    @GetMapping("/{userId}")
    public String show(@PathVariable("userId") Integer id, Model model) {
        List<Post> postByUser = postRepository.findByAuthor_Id(id);
        Optional<User> user = userRepository.findById(id);

        user.ifPresent(u -> model.addAttribute("user", u.getFullName()));
        model.addAttribute("catalogTitle", user.get().getFullName());
        model.addAttribute("posts", postByUser);

        return "user/catalog";
    }

}
