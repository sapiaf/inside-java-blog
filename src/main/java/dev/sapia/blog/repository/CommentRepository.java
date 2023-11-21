package dev.sapia.blog.repository;

import dev.sapia.blog.model.Comment;
import dev.sapia.blog.model.Message;
import dev.sapia.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPost_Id(Integer categoryId);
    List<Comment> findByContentContainingIgnoreCase(String query);
    List<Comment> findTop10ByOrderByDateTimeDesc();

}
