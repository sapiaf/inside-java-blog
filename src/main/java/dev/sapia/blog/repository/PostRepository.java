package dev.sapia.blog.repository;

import dev.sapia.blog.model.Post;
import dev.sapia.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByCategory_Id(Integer categoryId);
    List<Post> findByAuthor_Id(Integer authorId);
    int countByAuthorId(Integer authorId);
    List<Post> findByTitleContainingIgnoreCase(String query);
    List<Post> findAllByOrderByDateDesc();
    List<Post> findByAuthor_IdOrderByDateDesc(Integer authorId);
    @Query("SELECT COUNT(p) FROM Post p WHERE p.category.id = :categoryId")
    int countPostsByCategory(@Param("categoryId") Integer categoryId);
    @Query(value = "SELECT DATE_FORMAT(date, '%Y-%m') AS month, SUM(views) AS views " +
            "FROM posts GROUP BY DATE_FORMAT(date, '%Y-%m')", nativeQuery = true)
    List<Object[]> findTotalViewsPerMonth();

}
