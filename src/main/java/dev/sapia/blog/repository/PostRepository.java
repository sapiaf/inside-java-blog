package dev.sapia.blog.repository;

import dev.sapia.blog.model.Post;
import dev.sapia.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByCategory_Id(Integer categoryId);
    List<Post> findByAuthor_Id(Integer authorId);
    int countByAuthorId(Integer authorId);
}
