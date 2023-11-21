package dev.sapia.blog.controller.user;

import dev.sapia.blog.model.Message;
import dev.sapia.blog.repository.MessageRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public String showContactForm(Model model) {
        model.addAttribute("message", new Message());
        return "user/contact";
    }

    @PostMapping("/create")
    public String createMessage(@Valid @ModelAttribute("message") Message message, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/contact";
        } else {
            message.setDate(LocalDateTime.now());
            messageRepository.save(message);
            // Redirect alla pagina di conferma o a un'altra pagina
            return "redirect:/contact"; // Ad esempio
        }
    }
}
