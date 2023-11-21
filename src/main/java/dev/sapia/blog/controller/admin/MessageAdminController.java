package dev.sapia.blog.controller.admin;

import dev.sapia.blog.model.Comment;
import dev.sapia.blog.model.Message;
import dev.sapia.blog.model.Post;
import dev.sapia.blog.model.User;
import dev.sapia.blog.repository.MessageRepository;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/messages")
public class MessageAdminController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public String index(@RequestParam(value = "query", required = false) String searchKeyword, Model model) {
        List<Message> messages;
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            messages = messageRepository.findByTextContainingIgnoreCase(searchKeyword);
        } else {
            messages = messageRepository.findAll();
        }
        model.addAttribute("messages", messages);
        return "admin/message/list";
    }
    @GetMapping("/{messageId}")
    public String show(@PathVariable("messageId") Integer id, Model model) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            model.addAttribute("message", message);
            return "admin/message/detail";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        messageRepository.deleteById(id);
        return "redirect:/admin/messages";
    }

}
