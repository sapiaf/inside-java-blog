package dev.sapia.blog.controller.admin;

import dev.sapia.blog.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/messages")
public class MessageAdminController {
    @Autowired
    private MessageRepository messageRepository;

}
