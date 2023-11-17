package dev.sapia.blog.repository;

import dev.sapia.blog.model.Post;
import dev.sapia.blog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
