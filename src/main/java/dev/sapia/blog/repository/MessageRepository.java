package dev.sapia.blog.repository;

import dev.sapia.blog.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByTextContainingIgnoreCase(String query);
    List<Message> findTop10ByOrderByDateDesc();
}
