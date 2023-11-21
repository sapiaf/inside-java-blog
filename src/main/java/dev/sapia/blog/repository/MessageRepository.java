package dev.sapia.blog.repository;

import dev.sapia.blog.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
